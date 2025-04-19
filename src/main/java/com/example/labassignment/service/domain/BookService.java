package com.example.labassignment.service.domain;

import com.example.labassignment.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional <Book> save(Book book);
    void deleteById(Long id);
    Optional <Book> update(Long id, Book book);
    Book markAsRented(Long id);
    List<Book> findByName(String bookName, String authorName );
}
