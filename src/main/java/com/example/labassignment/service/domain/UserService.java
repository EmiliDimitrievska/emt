package com.example.labassignment.service.domain;

import com.example.labassignment.model.domain.User;
import com.example.labassignment.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role );
    User login(String username, String password);
    User  findByUsername(String username);

}
