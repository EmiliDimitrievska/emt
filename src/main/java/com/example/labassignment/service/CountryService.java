package com.example.labassignment.service;

import com.example.labassignment.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Country save(Country country);
    void deleteById(Long id);

}
