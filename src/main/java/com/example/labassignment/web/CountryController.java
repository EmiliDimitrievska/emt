package com.example.labassignment.web;

import com.example.labassignment.dto.createDtos.CreateCountryDto;
import com.example.labassignment.dto.displayDtos.DisplayCountryDto;
import com.example.labassignment.dto.updateDtos.UpdateCountryDto;
import com.example.labassignment.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "*")
@Tag(name = "Country API", description = "Endpoints for managing countries") // Swagger Tag for grouping
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Fetches all countries from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all countries"),
            @ApiResponse(responseCode = "404", description = "Countries not found")
    })
    @GetMapping
    public List<DisplayCountryDto> getAllCountries() {
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Get country by ID", description = "Fetches a country by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved country by ID"),
            @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> getCountryById(@PathVariable Long id) {
        return countryApplicationService.findById(id).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new country", description = "Creates a new country in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created country"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<CreateCountryDto> createCountry(@RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.save(createCountryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update country", description = "Updates an existing country by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated country"),
            @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @PostMapping("/update/{id}")
    public ResponseEntity<UpdateCountryDto> updateCountry(@PathVariable Long id, @RequestBody UpdateCountryDto country) {
        return countryApplicationService.update(id, country)
                .map(existing -> ResponseEntity.ok().body(existing))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete country", description = "Deletes a country by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted country"),
            @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (countryApplicationService.findById(id).isPresent()) {
            countryApplicationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
