package com.example.demo.repository;

import com.example.demo.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Keyword WHERE name LIKE %:name%")
    Set<Keyword> findByName(@Param("name") String name);
}
