package com.example.demo.repository;

import com.example.demo.model.ActorRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ActorRatingRepository extends JpaRepository<ActorRating, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM actor_rating WHERE user_id = :userId")
    ActorRating findByUser(@Param("userId") Integer userId);

    @Query(nativeQuery = true, value = "SELECT * FROM actor_rating WHERE actor_id = :actorId")
    Set<ActorRating> findAllReviewsForActor(@Param("actorId") Integer actorId);

    @Query(nativeQuery = true, value = "SELECT AVG(rating) FROM actor_rating")
    Float computeAverageRating();
}
