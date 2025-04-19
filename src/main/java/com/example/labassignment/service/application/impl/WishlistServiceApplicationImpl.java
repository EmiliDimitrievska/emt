package com.example.labassignment.service.application.impl;

import com.example.labassignment.dto.WishlistDto;
import com.example.labassignment.dto.displayDtos.DisplayAuthorDto;
import com.example.labassignment.dto.displayDtos.DisplayBookDto;
import com.example.labassignment.model.domain.Author;
import com.example.labassignment.service.application.WishlistApplicationService;
import com.example.labassignment.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistServiceApplicationImpl implements WishlistApplicationService {

    private final WishlistService wishlistService;

    public WishlistServiceApplicationImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public List<DisplayBookDto> listAllBooksInWishlist(Long id) {
        return DisplayBookDto.from(wishlistService.listAllBooksInWishlist(id));
    }

    @Override
    public Optional<WishlistDto> findByUser(String username) {
        return wishlistService.findByUser(username).map(WishlistDto::from);
    }

    @Override
    public Optional<WishlistDto> addBookToWishlist(String username, Long bookId) {
        return wishlistService.addBookToWishlist(username,bookId).map(WishlistDto::from);
    }

    @Override
    public void rentAllBooksInWishlist(String username) {
        wishlistService.rentAllBooksInWishlist(username);
    }

    @Override
    public void rentBook(String username, Long id) {
        wishlistService.rentBook(username, id);
    }

    @Override
    public List<DisplayAuthorDto> sortAuthorsByWishlistCount(String username) {
        List<Author> authors = wishlistService.sortAuthorsByWishlistCount(username);

        return authors.stream()
                .map(author -> new DisplayAuthorDto(
                        author.getId(),
                        author.getName(),
                        author.getSurname(),
                        author.getCountry()))
                .collect(Collectors.toList());
    }

}
