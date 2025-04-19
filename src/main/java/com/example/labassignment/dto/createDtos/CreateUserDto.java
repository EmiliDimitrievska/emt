package com.example.labassignment.dto.createDtos;

import com.example.labassignment.model.domain.User;
import com.example.labassignment.model.enumerations.Role;
import com.example.labassignment.exceptions.BadCredentialsException;

public record CreateUserDto(String username, String password, String repeatPassword, String name, String surname, Role role) {

    public User toUser(){
        if(password.equals(repeatPassword)){
            return new User(username, password, name, surname, role);
        }
        else throw new BadCredentialsException();
    }
}
