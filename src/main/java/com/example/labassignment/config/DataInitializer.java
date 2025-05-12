package com.example.labassignment.config;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.model.domain.Book;
import com.example.labassignment.model.domain.Country;
import com.example.labassignment.model.domain.User;
import com.example.labassignment.model.enumerations.Category;
import com.example.labassignment.model.enumerations.Role;
import com.example.labassignment.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WishlistRepository wishlistRepository;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, WishlistRepository wishlistRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.wishlistRepository = wishlistRepository;
    }

    //@PostConstruct
    public void init(){
        Country country1=new Country("country1", "Country 1");
        Country country2=new Country("country2", "Country 2");
        countryRepository.save(country1);
        countryRepository.save(country2);

        Author author1=new Author("author1", "Author 1", country1 );
        Author author2=new Author("author2", "Author 2", country2);
        authorRepository.save(author1);
        authorRepository.save(author2);

        Book book1=new Book("book1", Category.THRILLER, author1, 10 );
        Book book2=new Book("book2", Category.BIOGRAPHY, author2, 5 );
        Book book3=new Book("book2", Category.BIOGRAPHY, author2, 5 );
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        userRepository.save(new User("admin", passwordEncoder.encode("admin"), "Admin", "Admin", Role.ROLE_LIBRARIAN));
        userRepository.save(new User("user", passwordEncoder.encode("user"), "UserName", "UserSurname", Role.ROLE_USER));
        userRepository.save(new User("emili", passwordEncoder.encode("emili"), "Emili", "Dimitrievska", Role.ROLE_USER));

    }
}
