package com.example.labassignment.web;

import com.example.labassignment.dto.book.CreateBookDto;
import com.example.labassignment.dto.book.DisplayBookDto;
import com.example.labassignment.dto.book.UpdateBookDto;
import com.example.labassignment.model.views.BooksPerAuthorView;
import com.example.labassignment.service.application.BookApplicationService;
import com.example.labassignment.service.domain.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
@Tag(name = "Book API", description = "Endpoints for managing books")
public class BookController {
    private final BookService bookService;
    private final BookApplicationService bookApplicationService;

    public BookController(BookService bookService, BookApplicationService bookApplicationService) {
        this.bookService = bookService;
        this.bookApplicationService = bookApplicationService;
    }

    @Operation(summary = "Get all books", description = "Fetches all books from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all books"),
            @ApiResponse(responseCode = "404", description = "Books not found")
    })
    @GetMapping
    public List<DisplayBookDto> getAllBooks() {
        return bookApplicationService.findAll();
    }

    @Operation(summary = "Get book by ID", description = "Fetches a book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved book by ID"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> getBookById(@PathVariable Long id) {
        return bookApplicationService.findById(id).map(existing -> ResponseEntity.ok().body(existing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new book", description = "Creates a new book in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created book"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<CreateBookDto> createBook(@RequestBody CreateBookDto book) {
        return bookApplicationService.save(book)
                .map(existing -> ResponseEntity.ok().body(existing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update book", description = "Updates an existing book by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateBookDto> updateBook(@PathVariable Long id, @RequestBody UpdateBookDto book) {
        return bookApplicationService.update(id, book)
                .map(existing -> ResponseEntity.ok().body(existing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete book", description = "Deletes a book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted book"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Mark book as rented", description = "Marks a book as rented by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully marked book as rented"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/rent/{id}")
    public ResponseEntity<UpdateBookDto> markAsRented(@PathVariable Long id) {
        return bookApplicationService.markAsRented(id)
                .map(existing -> ResponseEntity.ok().body(existing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Search books by name and author", description = "Searches for books by name and author.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved books"),
            @ApiResponse(responseCode = "404", description = "No books found")
    })
    @GetMapping("/search")
    public ResponseEntity<List<DisplayBookDto>> getBooksByName(@RequestParam String bookName, @RequestParam String authorName) {
        List<DisplayBookDto> books = bookApplicationService.findByName(bookName, authorName);

        if (books.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no books found
        }

        return ResponseEntity.ok().body(books); // Return 200 with the list
    }

    @GetMapping("/by-author")
    public ResponseEntity<List<BooksPerAuthorView>> findBooksPerAuthor(){
        return ResponseEntity.ok(bookApplicationService.findBooksPerAuthor());
    }
}
