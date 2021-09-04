package com.example.demo.service;

import com.example.demo.model.dto.ActorDTO;
import com.example.demo.model.dto.RatingDTO;

import java.util.Set;

public interface ActorService {

    Set<ActorDTO> retrieveAllActors();

    Set<ActorDTO> retrieveAllActorsByName(String name);

    Set<ActorDTO> retrieveTopActors(Integer top);

    ActorDTO createActor(ActorDTO actorDTO);

    void deleteActorById(Integer id);

    Set<RatingDTO> retrieveAllReviews(Integer id);
}
