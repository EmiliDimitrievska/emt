package com.example.labassignment.dto.updateDtos;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.domain.Book;
import com.example.labassignment.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateBookDto(Long id,
                            String name,
                            Category category,
                            Long author,
                            Integer availableCopies)
{
    public static UpdateBookDto from(Book book){
        return new UpdateBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getAvailableCopies()
        );
    }
    public static List<UpdateBookDto> from(List<Book> books){
        return books.stream().map(UpdateBookDto::from).collect(Collectors.toList());
    }
    public Book toBook(Author author){
        return new Book(name, category, author, availableCopies);
    }

}
