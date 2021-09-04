package com.example.demo.repository;

import com.example.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Genre WHERE name LIKE %:name%")
    Set<Genre> findByName(@Param("name") String name);
}
