package com.example.labassignment.service.application;

import com.example.labassignment.dto.createDtos.CreateAuthorDto;
import com.example.labassignment.dto.displayDtos.DisplayAuthorDto;
import com.example.labassignment.dto.updateDtos.UpdateAuthorDto;

import java.util.List;
import java.util.Optional;


public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();
    Optional<DisplayAuthorDto> findById(Long id);
    Optional <CreateAuthorDto> save(CreateAuthorDto createAuthorDto);
    void deleteById(Long id);
    List<DisplayAuthorDto>findByName(String name);
    Optional <UpdateAuthorDto> update(Long id, UpdateAuthorDto updateAuthorDto);
}
