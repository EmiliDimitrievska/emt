package com.example.labassignment.service.domain;

import com.example.labassignment.model.domain.Wishlist;
import com.example.labassignment.model.domain.Book;
import com.example.labassignment.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    List<Book> listAllBooksInWishlist(Long id);

    Optional<Wishlist> addBookToWishlist(String username, Long bookId);

    Optional<Wishlist> findByUser(String username);

    void rentAllBooksInWishlist(String username);

    void rentBook(String username, Long id);

    List<Author> sortAuthorsByWishlistCount(String username);
}
