package com.example.demo.service;

import com.example.demo.model.Actor;
import com.example.demo.model.Constants;
import com.example.demo.model.dto.ActorDTO;
import com.example.demo.model.dto.RatingDTO;
import com.example.demo.repository.ActorRatingRepository;
import com.example.demo.repository.ActorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActorServiceImplementation implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorRatingRepository actorRatingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<ActorDTO> retrieveAllActors() {
        return actorRepository.findAll()
                .stream()
                .map(ActorDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ActorDTO> retrieveAllActorsByName(String name) {
        return actorRepository.findAllByName(name)
                .stream()
                .map(ActorDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ActorDTO> retrieveTopActors(Integer top) {
        return actorRepository.findTopByRating(top)
                .stream()
                .map(ActorDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public ActorDTO createActor(ActorDTO actorDTO) {
        if (!isActorDataValid(actorDTO)) {
            return null;
        }

        Actor actor = modelMapper.map(actorDTO, Actor.class);
        actor.setRating((float) 0);
        actor = actorRepository.save(actor);
        return ActorDTO.fromEntity(actor);
    }

    @Override
    public void deleteActorById(Integer id) {
        actorRepository.deleteById(id);
    }


    private boolean isActorDataValid(ActorDTO actorDTO) {
        if (actorDTO.getName().matches("[a-z A-Z]+")) {
            return true;
        }
        return false;
    }

    @Override
    public Set<RatingDTO> retrieveAllReviews(Integer id) {
        return actorRatingRepository.findAllReviewsForActor(id)
                .stream()
                .map(RatingDTO::fromEntity)
                .collect(Collectors.toSet());
    }

}
