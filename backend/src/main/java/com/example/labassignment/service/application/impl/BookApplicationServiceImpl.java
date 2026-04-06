package com.example.labassignment.service.application.impl;

import com.example.labassignment.dto.book.CreateBookDto;
import com.example.labassignment.dto.book.DisplayBookDto;
import com.example.labassignment.dto.book.UpdateBookDto;
import com.example.labassignment.model.views.BooksPerAuthorView;
import com.example.labassignment.service.application.BookApplicationService;
import com.example.labassignment.service.domain.AuthorService;
import com.example.labassignment.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;


    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService=authorService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<CreateBookDto> save(CreateBookDto book) {
        return authorService.findById(book.author())
                .flatMap(author -> bookService.save(book.toBook(author)))
                .map(CreateBookDto::from);
    }
 /*   @Override
    public Optional<CreateBookDto> save(CreateBookDto book) {

        Optional<Author> author=authorService.findById(book.author());

        return bookService.save(book.toBook(author.orElse(null))).map(CreateBookDto::from);
     *//*   if(author.isPresent()) {
            return bookService.save(book.toBook(author.get())).map(CreateBookDto::from);
        }
        return Optional.empty();
*//*
    }*/

    @Override
    public void deleteById(Long id) {
            bookService.deleteById(id);
    }

    @Override
    public Optional<UpdateBookDto> markAsRented(Long id) {
        return bookService.findById(id)
                .map(book -> UpdateBookDto.from(bookService.markAsRented(id)));
    }
    @Override
    public Optional<UpdateBookDto> update(Long id, UpdateBookDto book) {
        return authorService.findById(book.author())
                .flatMap(author -> bookService.update(id, book.toBook(author)))
                .map(UpdateBookDto::from);
    }

    @Override
    public List<BooksPerAuthorView> findBooksPerAuthor() {
        return bookService.findBooksPerAuthor();
    }

    /*@Override
    public Optional<UpdateBookDto>update(Long id, UpdateBookDto book) {
        Optional<Author>author =authorService.findById(book.author());
        return bookService.update(id, book.toBook(author.orElse(null))).map(UpdateBookDto::from);
    }*/

    @Override
    public List<DisplayBookDto> findByName(String bookName, String authorName) {
        return DisplayBookDto.from(bookService.findByName(bookName,authorName));
    }
}
