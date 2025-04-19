package com.example.labassignment.web;

import com.example.labassignment.dto.createDtos.CreateAuthorDto;
import com.example.labassignment.dto.displayDtos.DisplayAuthorDto;
import com.example.labassignment.dto.updateDtos.UpdateAuthorDto;
import com.example.labassignment.service.application.AuthorApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
@Tag(name = "Author API", description = "Endpoints for managing authors") // Swagger Tag for grouping
public class AuthorController {
    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @Operation(summary = "Get all authors", description = "Fetches all authors from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all authors"),
            @ApiResponse(responseCode = "404", description = "Authors not found")
    })
    @GetMapping
    public List<DisplayAuthorDto> getAllAuthors() {
        return authorApplicationService.findAll();
    }

    @Operation(summary = "Get author by ID", description = "Fetches an author by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved author by ID"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> getAuthorById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(existing -> ResponseEntity.ok().body(existing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new author", description = "Creates a new author in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created author"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<CreateAuthorDto> createAuthor(@RequestBody CreateAuthorDto author) {
        return authorApplicationService.save(author)
                .map(existing -> ResponseEntity.ok().body(existing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing author", description = "Updates an author by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated author"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateAuthorDto> updateAuthor(@PathVariable Long id, @RequestBody UpdateAuthorDto author) {
        return authorApplicationService.update(id, author)
                .map(existing -> ResponseEntity.ok().body(existing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an author", description = "Deletes an author by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted author"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Search authors by name", description = "Searches for authors by their name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved authors"),
            @ApiResponse(responseCode = "404", description = "No authors found")
    })
    @GetMapping("/search")
    public ResponseEntity<List<DisplayAuthorDto>> searchAuthor(@RequestParam String name) {
        List<DisplayAuthorDto> authors = authorApplicationService.findByName(name);
        if (authors.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(authors);
    }
}
