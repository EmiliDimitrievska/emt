package com.example.labassignment.service.domain.impl;

import com.example.labassignment.model.domain.Country;
import com.example.labassignment.repository.CountryRepository;
import com.example.labassignment.service.domain.CountryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
   // private final CountryService countryService;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional <Country> save(Country country) {
        return Optional.of(countryRepository.save(country));
    }
    @Override
    public Optional<Country> update(Long id, Country country){
        return countryRepository.findById(id).map(existing->{
            if(country.getName()!=null&&!existing.getName().contains(country.getName())){
                existing.setName(country.getName());
            }
            if(country.getContinent()!=null&&!existing.getContinent().contains(country.getContinent())){
                existing.setContinent(country.getContinent());
            }
            return countryRepository.save(existing);
        });
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
