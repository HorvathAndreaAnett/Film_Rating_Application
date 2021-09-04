package com.example.demo.repository;

import com.example.demo.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM Actor WHERE name LIKE %:name%")
    List<Actor> findAllByName(@Param("name") String name);


    @Query(nativeQuery = true, value = "SELECT * FROM Actor ORDER BY rating DESC LIMIT :top")
    List<Actor> findTopByRating(@Param("top") Integer top);


}
