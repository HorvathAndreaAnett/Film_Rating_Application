package com.example.demo.service;

import com.example.demo.model.Actor;
import com.example.demo.model.ActorRating;
import com.example.demo.model.RateStrategyName;
import com.example.demo.model.dto.RatingDTO;
import com.example.demo.repository.ActorRatingRepository;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ActorRatingStrategy implements RateStrategy {

    @Autowired
    private ActorRatingRepository actorRatingRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RatingDTO rate(RatingDTO ratingDTO) {
        if (!isRatingValid(ratingDTO)) {
            return null;
        }
        ActorRating actorRating = modelMapper.map(ratingDTO, ActorRating.class);
        actorRating.setActor(actorRepository.getOne(ratingDTO.getItemId()));
        actorRating.setUser(userRepository.getOne(ratingDTO.getUserId()));
        ActorRating existentRating = actorRatingRepository.findByUser(ratingDTO.getUserId());
        if (existentRating != null) {
            actorRating.setId(existentRating.getId());
        }
        actorRating = actorRatingRepository.save(actorRating);

        Actor actor = actorRepository.getOne(ratingDTO.getItemId());
        Float newAverageRating = actorRatingRepository.computeAverageRating();
        actor.setRating(newAverageRating);
        actorRepository.save(actor);

        return RatingDTO.fromEntity(actorRating);
    }

    private boolean isRatingValid(RatingDTO ratingDTO) {
        if (actorRepository.existsById(ratingDTO.getItemId())
                && ratingDTO.getRating() > 0 && ratingDTO.getRating() <= 10) {
            return true;
        }
        return false;
    }

    @Override
    public RateStrategyName getRateStrategyName() {
        return RateStrategyName.RATE_ACTOR;
    }
}
