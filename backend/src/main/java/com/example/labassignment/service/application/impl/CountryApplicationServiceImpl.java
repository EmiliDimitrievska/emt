package com.example.labassignment.service.application.impl;

import com.example.labassignment.dto.country.CreateCountryDto;
import com.example.labassignment.dto.country.DisplayCountryDto;
import com.example.labassignment.dto.country.UpdateCountryDto;
import com.example.labassignment.service.application.CountryApplicationService;
import com.example.labassignment.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<CreateCountryDto> save(CreateCountryDto country) {
        return countryService.save(country.toCountry()).map(CreateCountryDto::from);
    }

    @Override
    public Optional<UpdateCountryDto> update(Long id, UpdateCountryDto updateCountryDto) {
        return countryService.update(id, updateCountryDto.toCountry()).map(UpdateCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
