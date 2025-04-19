package com.example.labassignment.service.application;

import com.example.labassignment.dto.createDtos.CreateCountryDto;
import com.example.labassignment.dto.displayDtos.DisplayCountryDto;
import com.example.labassignment.dto.updateDtos.UpdateCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(Long id);
    Optional <CreateCountryDto> save(CreateCountryDto country);
    Optional<UpdateCountryDto> update(Long id, UpdateCountryDto updateCountryDto);
    void deleteById(Long id);
}
