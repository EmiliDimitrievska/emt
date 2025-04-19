package com.example.labassignment.service.application;

import com.example.labassignment.dto.WishlistDto;
import com.example.labassignment.dto.displayDtos.DisplayAuthorDto;
import com.example.labassignment.dto.displayDtos.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface WishlistApplicationService {
    List<DisplayBookDto> listAllBooksInWishlist(Long id);
    Optional<WishlistDto> findByUser(String username);
    Optional<WishlistDto> addBookToWishlist(String username, Long bookId);
    void rentAllBooksInWishlist(String username);
    void  rentBook(String username, Long id);
    List<DisplayAuthorDto> sortAuthorsByWishlistCount(String username);
}
