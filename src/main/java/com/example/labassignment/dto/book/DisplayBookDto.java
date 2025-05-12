package com.example.labassignment.dto.book;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.domain.Book;
import com.example.labassignment.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto
        (Long id,
         String name,
         Category category,
         Author author,
         Integer availableCopies) {
    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor(),
                book.getAvailableCopies()
        );
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }
    public Book toBook(Author author){
        return new Book(name, category, author, availableCopies);
    }


}
