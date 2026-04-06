package com.example.labassignment.repository;


import com.example.labassignment.model.views.BooksPerAuthorView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksPerAuthorViewRepository extends JpaRepository<BooksPerAuthorView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)//modifying kazuva deka ova query ne vrakja data, tuku pravi refresh
    @SuppressWarnings("SqlResolve")//true delot kazuva deka otkako kje se promeni treba hibrante da se updatira od cache
    @Query(value = "REFRESH MATERIALIZED VIEW public.books_per_author", nativeQuery = true)//this is sql not jb1l
    void refreshMaterializedView();

}
