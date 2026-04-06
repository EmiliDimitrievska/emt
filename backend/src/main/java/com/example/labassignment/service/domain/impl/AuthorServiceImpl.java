package com.example.labassignment.service.domain.impl;

import com.example.labassignment.events.AuthorEvent;
import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.domain.Country;
import com.example.labassignment.model.views.AuthorsPerCountryView;
import com.example.labassignment.projections.AuthorProjection;
import com.example.labassignment.repository.AuthorRepository;
import com.example.labassignment.repository.AuthorsPerCountryViewRepository;
import com.example.labassignment.service.application.AuthorApplicationService;
import com.example.labassignment.service.domain.AuthorService;
import com.example.labassignment.service.domain.CountryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;
    private final AuthorsPerCountryViewRepository authorPerCountryViewRepository;
    private ApplicationEventPublisher applicationEventPublisher;

public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService, AuthorsPerCountryViewRepository authorPerCountryViewRepository){
    this.authorRepository = authorRepository;
    this.countryService = countryService;
    this.authorPerCountryViewRepository = authorPerCountryViewRepository;
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
        Author author1=authorRepository.save(author); // prvo go sochuvame
        AuthorEvent authorEvent=new AuthorEvent(author1); // pravime event
        applicationEventPublisher.publishEvent(authorEvent); //go prakjame evenetot
        return Optional.of(author1);
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
                    authorRepository.save(existing);
                    AuthorEvent authorEvent=new AuthorEvent(existing);
                    applicationEventPublisher.publishEvent(authorEvent);
                    return existing;
                });

    }

    @Override
    public void refreshMaterializedView() {
        authorPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<AuthorsPerCountryView> findAuthorsPerCountry() {
        return authorPerCountryViewRepository.findAll();
    }

    @Override
    public List<AuthorProjection> findAllByNameAndSurname() {
        return authorRepository.findAllByNameAndSurname();
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
        AuthorEvent authorEvent=new AuthorEvent(null);
        applicationEventPublisher.publishEvent(authorEvent);
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }
}
