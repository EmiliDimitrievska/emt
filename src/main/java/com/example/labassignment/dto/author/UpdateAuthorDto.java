package com.example.labassignment.dto.author;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateAuthorDto(Long id,
                              String name,
                              String surname,
                              Long country) {

    public static UpdateAuthorDto from(Author author) {
        return new UpdateAuthorDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }

    public static List<UpdateAuthorDto> from(List<Author> authors) { // each element to be displayed with id
        return authors.stream().map(UpdateAuthorDto::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
