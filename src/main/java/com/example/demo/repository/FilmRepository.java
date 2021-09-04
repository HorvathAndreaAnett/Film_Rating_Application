package com.example.demo.repository;

import com.example.demo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Film ORDER BY rating DESC LIMIT :top")
    List<Film> findTopByRating(@Param("top") Integer top);

    @Query(nativeQuery = true, value = "SELECT * FROM Film WHERE name LIKE %:name%")
    List<Film> findByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * FROM Film WHERE YEAR(release_date) BETWEEN :lowLimit AND :upperLimit")
    List<Film> findByReleaseYearInterval(@Param("lowLimit") Integer lowLimit, @Param("upperLimit") Integer upperLimit);

    @Query(nativeQuery = true, value = "SELECT * FROM Film WHERE YEAR(release_date) = :year")
    List<Film> findByReleaseYear(@Param("year") Integer year);
}
