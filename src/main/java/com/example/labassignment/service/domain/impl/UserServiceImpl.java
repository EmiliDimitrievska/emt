package com.example.labassignment.service.domain.impl;

import com.example.labassignment.model.domain.User;
import com.example.labassignment.model.enumerations.Role;
import com.example.labassignment.exceptions.BadCredentialsException;
import com.example.labassignment.repository.UserRepository;
import com.example.labassignment.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || password == null || repeatPassword == null || name == null || surname == null) {
            throw new BadCredentialsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new BadCredentialsException();
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new BadCredentialsException();
        }
        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new BadCredentialsException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(BadCredentialsException::new);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(BadCredentialsException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(BadCredentialsException::new);
    }
}
