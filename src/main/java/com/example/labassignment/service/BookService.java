package com.example.labassignment.service;

import com.example.labassignment.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
    Book markAsRented(Long id);
    public List<Book> findByName(String bookName, String authorName );
}
