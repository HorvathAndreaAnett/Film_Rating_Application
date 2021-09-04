package com.example.demo.service;

import com.example.demo.model.dto.FilmDTO;
import com.example.demo.model.dto.RatingDTO;

import java.util.Set;

public interface FilmService {

    Set<FilmDTO> retrieveAllFilms();

    Set<FilmDTO> retrieveAllFilmsByName(String filmName);

    Set<FilmDTO> retrieveAllFilmsByGenre(Set<Integer> genreIds);

    Set<FilmDTO> retrieveAllFilmsByKeyword(Set<Integer> keywordIds);

    Set<FilmDTO> retrieveAllFilmsByActors(Set<Integer> actorsId);

    FilmDTO createFilm(FilmDTO filmDTO);

    void deleteFilmById(Integer id);

    Set<RatingDTO> retrieveAllReviews(Integer id);

    Set<FilmDTO> retrieveTopFilms(Integer top);

    Set<FilmDTO> retrieveAllFilmsReleasedInYear(Integer year);

    Set<FilmDTO > retrieveAllFilmsReleasedInPeriod(Integer lowerLimit, Integer upperLimit);
}
