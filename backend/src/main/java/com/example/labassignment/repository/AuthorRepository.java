package com.example.labassignment.repository;

import com.example.labassignment.model.domain.Author;
import com.example.labassignment.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String firstName);
    @Query("select a from Author a")
    List <AuthorProjection> findAllByNameAndSurname();
}
