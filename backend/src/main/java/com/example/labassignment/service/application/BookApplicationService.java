package com.example.labassignment.service.application;

import com.example.labassignment.dto.book.CreateBookDto;
import com.example.labassignment.dto.book.DisplayBookDto;
import com.example.labassignment.dto.book.UpdateBookDto;
import com.example.labassignment.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;


public interface BookApplicationService {
    List<DisplayBookDto> findAll();
    Optional<DisplayBookDto> findById(Long id);
    Optional <CreateBookDto> save(CreateBookDto book);
    void deleteById(Long id);
    Optional <UpdateBookDto> markAsRented(Long id);
    List<DisplayBookDto> findByName(String bookName, String authorName );
    Optional<UpdateBookDto>update(Long id,UpdateBookDto book);
    List<BooksPerAuthorView>findBooksPerAuthor();
}
