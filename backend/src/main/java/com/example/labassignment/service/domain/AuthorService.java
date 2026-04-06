package com.example.labassignment.service.domain;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.views.AuthorsPerCountryView;
import com.example.labassignment.projections.AuthorProjection;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional <Author> save(Author author);
    void deleteById(Long id);
    List<Author>findByName(String name);
    Optional <Author> update(Long id, Author author);
    void refreshMaterializedView();
    List<AuthorsPerCountryView>findAuthorsPerCountry();
    List<AuthorProjection>findAllByNameAndSurname();
}
