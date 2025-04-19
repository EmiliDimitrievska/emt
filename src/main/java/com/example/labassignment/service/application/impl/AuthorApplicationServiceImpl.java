package com.example.labassignment.service.application.impl;


import com.example.labassignment.dto.createDtos.CreateAuthorDto;
import com.example.labassignment.dto.displayDtos.DisplayAuthorDto;
import com.example.labassignment.dto.updateDtos.UpdateAuthorDto;
import com.example.labassignment.service.application.AuthorApplicationService;
import com.example.labassignment.service.domain.AuthorService;
import com.example.labassignment.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<CreateAuthorDto> save(CreateAuthorDto createAuthorDto) {
        return countryService.findById(createAuthorDto.countryId())
                .flatMap(country -> authorService.save(createAuthorDto.toAuthor(country)))
                .map(CreateAuthorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }

    @Override
    public Optional<UpdateAuthorDto> update(Long id, UpdateAuthorDto updateAuthorDto) {
        return countryService.findById(updateAuthorDto.country())
                .flatMap(country -> authorService.update(id, updateAuthorDto.toAuthor(country)))
                .map(UpdateAuthorDto::from);
    }

    @Override
    public List<DisplayAuthorDto> findByName(String name) {
        return DisplayAuthorDto.from(authorService.findByName(name));
    }
}
