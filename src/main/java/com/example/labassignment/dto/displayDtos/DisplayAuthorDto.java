package com.example.labassignment.dto.displayDtos;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDto(Long id, String name, String surname, Country country) {

    public static DisplayAuthorDto from(Author author){
        return new DisplayAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry());
    }

    public static List<DisplayAuthorDto> from(List<Author> authors){
        return authors.stream().map(DisplayAuthorDto::from).collect(Collectors.toList());
    }
    public Author toAuthor() {
        return new Author(this.id, this.name, this.surname, this.country);
    }

}
