package com.example.labassignment.web;

import com.example.labassignment.dto.auth.LoginUserDto;
import com.example.labassignment.dto.createDtos.CreateUserDto;
import com.example.labassignment.dto.displayDtos.DisplayUserDto;
import com.example.labassignment.exceptions.BadCredentialsException;
import com.example.labassignment.service.application.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto){
        return userApplicationService.register(createUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(@RequestBody LoginUserDto loginUserDto, HttpServletRequest request) {
        try {
            DisplayUserDto displayUserDto = userApplicationService.login(loginUserDto)
                    .orElseThrow(BadCredentialsException::new);

            request.getSession().setAttribute("user", displayUserDto.toUser());

            return ResponseEntity.ok(displayUserDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Return 401
        }

    }
    @Operation(summary = "User logout", description = "Ends the user's session")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
