package com.example.labassignment.service.domain;

import com.example.labassignment.model.domain.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional <Country> save(Country country);
    Optional<Country> update(Long id, Country country);
    void deleteById(Long id);

}
