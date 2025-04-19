package com.example.labassignment.model.domain;

import com.example.labassignment.model.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Relation;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Data
@Entity
@Table(name = "book-users")
public class User implements UserDetails {

    @Id
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;


    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_" + getRole().name()));
    }

    public User(){}
    public User (String username, String name, String surname,  Role role) {
        this.username = username;

        this.name = name;
        this.surname = surname;
        this.role = role;

    }

    public User (String username, String password,String name, String surname,  Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;

    }

    @Override
    public boolean isAccountNonExpired() {
        //  return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return this.role;
    }
}
