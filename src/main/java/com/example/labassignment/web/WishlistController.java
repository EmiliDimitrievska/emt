package com.example.labassignment.web;

import com.example.labassignment.dto.WishlistDto;
import com.example.labassignment.dto.author.DisplayAuthorDto;
import com.example.labassignment.service.application.WishlistApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "Wishlist API", description = "Endpoints for managing wishlist")
public class WishlistController {
    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @GetMapping
    @Operation(summary = "Get all books in wishlist", description = "Retrieve all books in wishlist")
    public ResponseEntity<WishlistDto> getWishlist(HttpServletRequest req) {
        String username = req.getRemoteUser();

        System.out.println("Username: " + username);

        if (username == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<WishlistDto> wishlist = wishlistApplicationService.findByUser(username);
        return wishlist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    @Operation(summary = "Add book to wishlist", description = "Add book to wishlist by book id")
    public ResponseEntity<WishlistDto> addBookToWishlist(@PathVariable Long id,
                                                         HttpServletRequest req) {


        String username = req.getRemoteUser();
        if (username == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<WishlistDto> updatedWishlist = wishlistApplicationService.addBookToWishlist(username, id);
        return updatedWishlist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/rent")
    @Operation(summary = "Rent all books in wishlist", description = "Rent all books in wishlist and reduce available copies")
    public ResponseEntity<String> rentAllBooks(HttpServletRequest req) {
        String username = req.getRemoteUser();

        if (username == null) {
            return ResponseEntity.status(401).body("Unauthorized!");
        }

        try {
            wishlistApplicationService.rentAllBooksInWishlist(username);
            return ResponseEntity.ok("All books rented successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/rent/{id}")
    @Operation(summary = "Rent a book from the wishlist", description = "Rent a specific book from the wishlist by book id")
    public ResponseEntity<WishlistDto> rentBookById(@PathVariable Long id, HttpServletRequest req) {
        String username = req.getRemoteUser();

        if (username == null) {
            return ResponseEntity.status(401).build();
        }

        try {
            wishlistApplicationService.rentBook(username, id);

            Optional<WishlistDto> updatedWishlist = wishlistApplicationService.findByUser(username);

            return updatedWishlist.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/sorted-authors")
    @Operation(summary = "Get authors sorted by wishlist count", description = "Retrieve authors sorted by the number of books in their wishlist")
    public ResponseEntity<List<DisplayAuthorDto>> getSortedAuthorsByWishlistCount(HttpServletRequest req) {

        String username = req.getRemoteUser();

        List<DisplayAuthorDto> sortedAuthors = wishlistApplicationService.sortAuthorsByWishlistCount(username);
        return ResponseEntity.ok(sortedAuthors);
    }

}


