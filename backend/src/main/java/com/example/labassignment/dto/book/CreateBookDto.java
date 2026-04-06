package com.example.labassignment.dto.book;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.domain.Book;
import com.example.labassignment.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(String name,
                            Category category,
                            Long author,
                            Integer availableCopies) {
    public static CreateBookDto from(Book book) {
        return new CreateBookDto(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }

    public static List<CreateBookDto> from(List<Book> books) {
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
