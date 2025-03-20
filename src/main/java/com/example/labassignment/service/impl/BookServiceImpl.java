package com.example.labassignment.service.impl;

import com.example.labassignment.model.Book;
import com.example.labassignment.repository.AuthorRepository;
import com.example.labassignment.repository.BookRepository;
import com.example.labassignment.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book markAsRented(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent() && book.get().getAvailableCopies() > 0) {
            book.get().setAvailableCopies(book.get().getAvailableCopies() - 1);
            return bookRepository.save(book.get());
        }
        return null;
    }
    @Override
    public List<Book> findByName(String bookName, String authorName) {
        return bookRepository.findAll().stream().filter(x->x.getName().contains(bookName)||x.getAuthor().getName().contains(authorName)).toList();
       // authorRepository.findByName(authorName)
    }
}
