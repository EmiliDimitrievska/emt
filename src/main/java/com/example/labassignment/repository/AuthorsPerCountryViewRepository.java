package com.example.labassignment.repository;

import com.example.labassignment.model.views.AuthorsPerCountryView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsPerCountryViewRepository extends JpaRepository<AuthorsPerCountryView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @SuppressWarnings("SqlResolve")
    @Query(value = "REFRESH MATERIALIZED VIEW public.authors_per_country", nativeQuery = true)
    void refreshMaterializedView();
}
