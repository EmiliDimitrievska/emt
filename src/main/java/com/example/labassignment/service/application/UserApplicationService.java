package com.example.labassignment.service.application;

import com.example.labassignment.dto.auth.LoginUserDto;
import com.example.labassignment.dto.createDtos.CreateUserDto;
import com.example.labassignment.dto.displayDtos.DisplayUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<DisplayUserDto> login (LoginUserDto loginUserDto);
    Optional<DisplayUserDto> findByUsername(String username);
}
