package com.example.labassignment.repository;

import com.example.labassignment.model.domain.User;
import com.example.labassignment.model.enumerations.Role;
import com.example.labassignment.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"wishlist"}
    )
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u")
    List<UserProjection> findAllProjectedBy();


    UserProjection findByRole(Role role);


}
