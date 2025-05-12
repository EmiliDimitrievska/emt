package com.example.labassignment.service.domain.impl;

import com.example.labassignment.exceptions.*;
import com.example.labassignment.model.domain.User;
import com.example.labassignment.model.domain.Wishlist;
import com.example.labassignment.model.enumerations.Role;
import com.example.labassignment.repository.UserRepository;
import com.example.labassignment.repository.WishlistRepository;
import com.example.labassignment.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WishlistRepository wishlistRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.wishlistRepository = wishlistRepository;
    }


    @Override
    public Optional<User> register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        Wishlist wishlist = new Wishlist();
        wishlistRepository.save(wishlist);

        User user = new User(username, passwordEncoder.encode(password), name, surname, role);

        user.setWishlist(wishlist);
        return Optional.of(userRepository.save(user));
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        }

        throw new InvalidUserCredentialsException();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        //   return userRepository.findByUsername(username).orElseThrow(BadCredentialsException::new);
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(BadCredentialsException::new);
    }
}
