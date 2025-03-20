package com.example.labassignment.controller;

import com.example.labassignment.model.Book;
import com.example.labassignment.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.save(book);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
    @PutMapping("/{id}/rent")
    public Book markAsRented(@PathVariable Long id) {
        return bookService.markAsRented(id);
    }
    @GetMapping("/search")
    public List<Book> getBooksByName(@RequestParam String bookName, @RequestParam String authorName) {
        return bookService.findByName(bookName, authorName);
    }


}
