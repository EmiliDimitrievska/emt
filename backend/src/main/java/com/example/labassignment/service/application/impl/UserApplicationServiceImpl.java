package com.example.labassignment.service.application.impl;

import com.example.labassignment.config.jwt.JwtHelper;
import com.example.labassignment.dto.user.LoginResponseDto;
import com.example.labassignment.dto.user.LoginUserDto;
import com.example.labassignment.dto.user.CreateUserDto;
import com.example.labassignment.dto.user.DisplayUserDto;
import com.example.labassignment.model.domain.User;
import com.example.labassignment.service.application.UserApplicationService;
import com.example.labassignment.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        Optional <User>user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );

        return Optional.of(DisplayUserDto.from(user.get()));

    }


    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        String token = jwtHelper.generateToken(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        ));
        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username).get()));
    }
}
