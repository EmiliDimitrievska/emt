package com.example.labassignment.service.application;

import com.example.labassignment.dto.country.CreateCountryDto;
import com.example.labassignment.dto.country.DisplayCountryDto;
import com.example.labassignment.dto.country.UpdateCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional <CreateCountryDto> save(CreateCountryDto country);
    Optional<UpdateCountryDto> update(Long id, UpdateCountryDto updateCountryDto);
    void deleteById(Long id);
}
