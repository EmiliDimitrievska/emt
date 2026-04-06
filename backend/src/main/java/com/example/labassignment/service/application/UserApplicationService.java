package com.example.labassignment.service.application;

import com.example.labassignment.dto.user.LoginResponseDto;
import com.example.labassignment.dto.user.LoginUserDto;
import com.example.labassignment.dto.user.CreateUserDto;
import com.example.labassignment.dto.user.DisplayUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<LoginResponseDto> login (LoginUserDto loginUserDto);
    Optional<DisplayUserDto> findByUsername(String username);
}
