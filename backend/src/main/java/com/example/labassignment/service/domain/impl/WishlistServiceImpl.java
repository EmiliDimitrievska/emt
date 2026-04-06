package com.example.labassignment.service.domain.impl;

import com.example.labassignment.exceptions.BookUnavailableException;
import com.example.labassignment.model.domain.User;
import com.example.labassignment.model.domain.Wishlist;
import com.example.labassignment.model.domain.Author;
import com.example.labassignment.repository.WishlistRepository;
import com.example.labassignment.service.domain.BookService;
import com.example.labassignment.service.domain.UserService;
import com.example.labassignment.service.domain.WishlistService;
import jakarta.transaction.Transactional;
import com.example.labassignment.model.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final BookService bookService;
    private final UserService userService;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, BookService bookService, UserService userService) {
        this.wishlistRepository = wishlistRepository;
        this.bookService = bookService;
        this.userService = userService;
    }


    @Override
    public List<Book> listAllBooksInWishlist(Long id) {
        if (wishlistRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Wishlist id empty!");
        }
        return wishlistRepository.findById(id).get().getBooks();
    }

    @Override
    public Optional<Wishlist> addBookToWishlist(String username, Long bookId) {
        User user = userService.findByUsername(username).get();

        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseGet(() -> wishlistRepository.save(new Wishlist(user, new ArrayList<>())));

        Book book = bookService.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        if (wishlist.getBooks().stream().anyMatch(b -> b.getId().equals(bookId))) {
            throw new RuntimeException("Book is already in wishlist!");
        }

        if (book.getAvailableCopies() > 0) {

            book.setWishlist(wishlist);
            wishlist.getBooks().add(book);

            wishlistRepository.save(wishlist);
            return Optional.of(wishlist);
        } else {
            throw new BookUnavailableException(book.getId());
        }
    }


    @Override
    public Optional<Wishlist> findByUser(String username) {
        User user = userService.findByUsername(username).get();
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        return wishlistRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void rentAllBooksInWishlist(String username) {
        User user = userService.findByUsername(username).get();
        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user!"));

        if (wishlist.getBooks().isEmpty()) {
            throw new RuntimeException("Wishlist is empty!");
        }

        List<Book> rentedBooks = new ArrayList<>();

        for (Book book : wishlist.getBooks()) {
            if (book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                rentedBooks.add(book);
            } else {
                throw new RuntimeException("Book '" + book.getName() + "' is not available for rent!");
            }
        }

        wishlist.getBooks().clear();
        wishlistRepository.save(wishlist);
    }

    @Override
    public void rentBook(String username, Long id) {
        User user = userService.findByUsername(username).get();
        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user!"));

        if (wishlist.getBooks().isEmpty()) {
            throw new RuntimeException("Wishlist is empty!");
        }

        Book book = bookService.findById(id).orElseThrow(() -> new RuntimeException("Book not found!"));
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
        wishlistRepository.save(wishlist);
    }

    @Override
    public List<Author> sortAuthorsByWishlistCount(String username) {
        User user = userService.findByUsername(username).get();
        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wishlist not found for user!"));

        return wishlist.getBooks().stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()))
                .entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
