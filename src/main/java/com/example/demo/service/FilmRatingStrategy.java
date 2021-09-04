package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.FilmRating;
import com.example.demo.model.RateStrategyName;
import com.example.demo.model.dto.RatingDTO;
import com.example.demo.repository.FilmRatingRepository;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FilmRatingStrategy implements RateStrategy {

    @Autowired
    private FilmRatingRepository filmRatingRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RatingDTO rate(RatingDTO ratingDTO) {
        if (!isRatingValid(ratingDTO)) {
            return null;
        }
        FilmRating filmRating = modelMapper.map(ratingDTO, FilmRating.class);
        filmRating.setFilm(filmRepository.getOne(ratingDTO.getItemId()));
        filmRating.setUser(userRepository.getOne(ratingDTO.getUserId()));
        FilmRating existentRating = filmRatingRepository.findByUser(ratingDTO.getUserId());
        if (existentRating != null) {
            filmRating.setId(existentRating.getId());
        }
        filmRating = filmRatingRepository.save(filmRating);

        Film film = filmRepository.getOne(ratingDTO.getItemId());
        Float newAverageRating = filmRatingRepository.computeAverageRating();
        film.setRating(newAverageRating);
        filmRepository.save(film);

        return RatingDTO.fromEntity(filmRating);
    }

    private boolean isRatingValid(RatingDTO ratingDTO) {
        if (filmRepository.existsById(ratingDTO.getItemId())
                && ratingDTO.getRating() > 0 && ratingDTO.getRating() <= 10) {
            return true;
        }
        return false;
    }

    @Override
    public RateStrategyName getRateStrategyName() {
        return RateStrategyName.RATE_FILM;
    }
}
