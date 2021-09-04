package com.example.demo.repository;

import com.example.demo.model.FilmRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FilmRatingRepository extends JpaRepository<FilmRating, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM film_rating WHERE user_id = :userId")
    FilmRating findByUser(@Param("userId") Integer userId);

    @Query(nativeQuery = true, value = "SELECT * FROM film_rating WHERE film_id = :filmId")
    Set<FilmRating> findAllReviewsForFilm(@Param("filmId") Integer filmId);

    @Query(nativeQuery = true, value = "SELECT AVG(rating) FROM film_rating")
    Float computeAverageRating();

}
