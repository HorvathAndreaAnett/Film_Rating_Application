package com.example.demo.service;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.model.Genre;
import com.example.demo.model.Keyword;
import com.example.demo.model.dto.FilmDTO;
import com.example.demo.model.dto.RatingDTO;
import com.example.demo.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmServiceImplementation implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRatingRepository filmRatingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<FilmDTO> retrieveAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(FilmDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FilmDTO> retrieveAllFilmsByName(String filmName) {
        return filmRepository.findByName(filmName)
                .stream()
                .map(FilmDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FilmDTO> retrieveAllFilmsByGenre(Set<Integer> genreIds) {
        return filmRepository.findAll()
                .stream()
                .filter(film -> {
                    Set<Integer> filmGenresIds = film.getGenreSet().stream().map(Genre::getId).collect(Collectors.toSet());
                    return filmGenresIds.containsAll(genreIds);
                })
                .map(FilmDTO::fromEntity)
                .collect(Collectors.toSet());
    }


    @Override
    public Set<FilmDTO> retrieveAllFilmsByKeyword(Set<Integer> keywordIds) {
        return filmRepository.findAll()
                .stream()
                .filter(film -> {
                    Set<Integer> filmKeywordsIds = film.getKeywordSet().stream().map(Keyword::getId).collect(Collectors.toSet());
                    return filmKeywordsIds.containsAll(keywordIds);
                })
                .map(FilmDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FilmDTO> retrieveAllFilmsByActors(Set<Integer> actorsId) {
        return filmRepository.findAll()
                .stream()
                .filter(film -> {
                    Set<Integer> filmActorsIds = film.getCast().stream().map(Actor::getId).collect(Collectors.toSet());
                    return filmActorsIds.containsAll(actorsId);
                })
                .map(FilmDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) {

        Film film = modelMapper.map(filmDTO, Film.class);

        Set<Genre> filmGenres = new HashSet<>();
        for (Integer genreId: filmDTO.getGenreIdSet()) {
            if (genreRepository.existsById(genreId)) {
                filmGenres.add(genreRepository.getOne(genreId));
            } else {
                return null;
            }
        }
        film.setGenreSet(filmGenres);

        Set<Keyword> filmKeywords = new HashSet<>();
        for (Integer keywordId: filmDTO.getKeywordIdSet()) {
            if (keywordRepository.existsById(keywordId)) {
                filmKeywords.add(keywordRepository.getOne(keywordId));
            } else {
                return null;
            }
        }
        film.setKeywordSet(filmKeywords);

        Set<Actor> filmActors = new HashSet<>();
        for (Integer actorId: filmDTO.getActorIdSet()) {
            if (actorRepository.existsById(actorId)) {
                filmActors.add(actorRepository.getOne(actorId));
            } else {
                return null;
            }
        }
        film.setCast(filmActors);
        film.setRating((float) 0);
        film = filmRepository.save(film);

        return FilmDTO.fromEntity(film);
    }

    @Override
    public void deleteFilmById(Integer id) {
        filmRepository.deleteById(id);
    }

    @Override
    public Set<RatingDTO> retrieveAllReviews(Integer id) {
        return filmRatingRepository.findAllReviewsForFilm(id)
                .stream()
                .map(RatingDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FilmDTO> retrieveTopFilms(Integer top) {
        return filmRepository.findTopByRating(top)
                .stream()
                .map(FilmDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FilmDTO> retrieveAllFilmsReleasedInYear(Integer year) {
        return filmRepository.findByReleaseYear(year)
                .stream()
                .map(FilmDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FilmDTO> retrieveAllFilmsReleasedInPeriod(Integer lowerLimit, Integer upperLimit) {
        return filmRepository.findByReleaseYearInterval(lowerLimit, upperLimit)
                .stream()
                .map(FilmDTO::fromEntity)
                .collect(Collectors.toSet());
    }
}
