package com.example.labassignment.service.application;

import com.example.labassignment.dto.author.CreateAuthorDto;
import com.example.labassignment.dto.author.DisplayAuthorDto;
import com.example.labassignment.dto.author.UpdateAuthorDto;
import com.example.labassignment.model.views.AuthorsPerCountryView;
import com.example.labassignment.projections.AuthorProjection;

import java.util.List;
import java.util.Optional;


public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();
    Optional<DisplayAuthorDto> findById(Long id);
    Optional <CreateAuthorDto> save(CreateAuthorDto createAuthorDto);
    void deleteById(Long id);
    List<DisplayAuthorDto>findByName(String name);
    Optional <UpdateAuthorDto> update(Long id, UpdateAuthorDto updateAuthorDto);
    List<AuthorsPerCountryView>findAuthorsPerCountry();
    public List<AuthorProjection> findAllByNameAndSurname();
}
