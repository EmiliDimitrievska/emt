package com.example.labassignment.service.domain.impl;

import com.example.labassignment.model.domain.Book;
import com.example.labassignment.model.views.BooksPerAuthorView;
import com.example.labassignment.repository.AuthorRepository;
import com.example.labassignment.repository.BookRepository;
import com.example.labassignment.repository.BooksPerAuthorViewRepository;
import com.example.labassignment.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
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
    public Optional <Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
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
    public Optional<Book>update(Long id, Book book){
        return bookRepository.findById(id).map(existing->{
            if(book.getName()!=null&&!existing.getName().contains(book.getName())){
                existing.setName(book.getName());
            }
            if(book.getAvailableCopies()!=null&&existing.getAvailableCopies()!= book.getAvailableCopies()){
                existing.setAvailableCopies(book.getAvailableCopies());
            }
            if(book.getCategory()!=null&&existing.getCategory()!=book.getCategory()){
                existing.setCategory(book.getCategory());
            }
            if(book.getAuthor()!=null&&bookRepository.findById(book.getAuthor().getId()).isPresent()){
                existing.setAuthor(book.getAuthor());
            }
            return bookRepository.save(existing);
        });
    }

    @Override
    public List<Book> findByName(String bookName, String authorName) {
        return bookRepository.findAll().stream().filter(x->x.getName().contains(bookName)||x.getAuthor().getName().contains(authorName)).toList();
       // authorRepository.findByName(authorName)
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }

    @Override
    public List<BooksPerAuthorView> findBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }
}
