package com.example.labassignment.service;

import com.example.labassignment.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Author save(Author author);
    void deleteById(Long id);
    public List<Author>findByName(String name);

}
