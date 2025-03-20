package com.example.labassignment.controller;

import com.example.labassignment.model.Country;
import com.example.labassignment.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "*")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Country> getCountryById(@PathVariable Long id) {
        return countryService.findById(id);
    }
    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryService.save(country);
    }
    @PostMapping("/{id}")
    public Country updateCountry(@PathVariable Long id, @RequestBody Country country) {
        return countryService.save(country);
    }
    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}
