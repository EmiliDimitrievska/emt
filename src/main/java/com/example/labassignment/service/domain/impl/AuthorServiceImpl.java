package com.example.labassignment.service.domain.impl;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.domain.Country;
import com.example.labassignment.repository.AuthorRepository;
import com.example.labassignment.service.application.AuthorApplicationService;
import com.example.labassignment.service.domain.AuthorService;
import com.example.labassignment.service.domain.CountryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
  //  private final AuthorService authorService;
    private final CountryService countryService;

public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService){
    this.authorRepository = authorRepository;
    this.countryService = countryService;
}

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id)
                .map(existing -> {
                    if (author.getName() != null && !author.getName().equals(existing.getName())) {
                        existing.setName(author.getName());
                    }
                    if (author.getSurname() != null && !author.getSurname().equals(existing.getSurname())) {
                        existing.setSurname(author.getSurname());
                    }
                    //  Optional<Country> country= countryService.update(author.getCountry().getId(), author.getCountry()); mislam deka ne se bara da se updatenuva country kako paratmeri na coutnry,tuku da se updateira vo authro kako celosen objekt
                    if (author.getCountry() != null && countryService.findById(author.getCountry().getId()).isPresent()) {
                        existing.setCountry(author.getCountry());
                    }
                    return authorRepository.save(existing);
                });

    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }
}
