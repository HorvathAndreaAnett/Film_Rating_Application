package com.example.demo.controller;

import com.example.demo.model.dto.FilmDTO;
import com.example.demo.model.dto.RatingDTO;
import com.example.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;


    @GetMapping("/retrieveAllFilms")
    public ResponseEntity<?> retrieveAllFilms() {
        Set<FilmDTO> filmDTOSet = filmService.retrieveAllFilms();
        if (filmDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmDTOSet, HttpStatus.OK);
    }

    @GetMapping("/retrieveAllFilmsByGenre")
    public ResponseEntity<?> retrieveAllFilmsByGenre(@RequestBody Set<Integer> genreIds) {
        Set<FilmDTO> filmDTOSet = filmService.retrieveAllFilmsByGenre(genreIds);
        if (filmDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmDTOSet, HttpStatus.OK);
    }

    @GetMapping("/retrieveAllFilmsByKeywords")
    public ResponseEntity<?> retrieveAllFilmsByKeyword(@RequestBody Set<Integer> keywordIds) {
        Set<FilmDTO> filmDTOSet = filmService.retrieveAllFilmsByKeyword(keywordIds);
        if (filmDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmDTOSet, HttpStatus.OK);
    }

    @GetMapping("/retrieveAllFilmsByActors")
    public ResponseEntity<?> retrieveAllFilmsByActors(@RequestBody Set<Integer> actorIds) {
        Set<FilmDTO> filmDTOSet = filmService.retrieveAllFilmsByActors(actorIds);
        if (filmDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmDTOSet, HttpStatus.OK);
    }

    @PostMapping("/administrator/createFilm")
    public ResponseEntity<?> createFilm(@RequestBody FilmDTO filmDTO) {
        FilmDTO createdFilm = filmService.createFilm(filmDTO);
        if (createdFilm == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdFilm, HttpStatus.OK);
    }

    @DeleteMapping("/administrator/deleteFilmById")
    public ResponseEntity<?> deleteFilmById(@RequestBody Integer filmId) {
        try {
            filmService.deleteFilmById(filmId);
            return new ResponseEntity<>("Film deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Film not found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/retrieveAllReviewsForFilm")
    public ResponseEntity<?> retrieveAllReviewsForFilm(@RequestBody Integer filmId) {
        Set<RatingDTO> reviews = filmService.retrieveAllReviews(filmId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/retrieveTopNFilms")
    public ResponseEntity<?> retrieveTopNFilms(@RequestBody Integer top) {
        Set<FilmDTO> topFilms = filmService.retrieveTopFilms(top);
        if (topFilms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(topFilms, HttpStatus.OK);
    }

    @GetMapping("/retrieveAllFilmsByName")
    public ResponseEntity<?> retrieveAllFilmsByName(@RequestBody String name) {
        Set<FilmDTO> filmDTOSet = filmService.retrieveAllFilmsByName(name);
        if (filmDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmDTOSet, HttpStatus.OK);
    }

    @GetMapping("/retrieveAllFilmsReleasedInYear")
    public ResponseEntity<?> retrieveAllFilmsReleasedInYear(@RequestBody Integer year) {
        Set<FilmDTO> filmDTOSet = filmService.retrieveAllFilmsReleasedInYear(year);
        if (filmDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmDTOSet, HttpStatus.OK);
    }

    @GetMapping("/retrieveAllFilmsReleasedInPeriod")
    public ResponseEntity<?> retrieveAllFilmsReleasedInPeriod(@RequestParam(value = "lowerLimitYear") Integer lowerLimit,
                                                              @RequestParam(value = "upperLimitYear") Integer upperLimit) {
        Set<FilmDTO> filmDTOSet = filmService.retrieveAllFilmsReleasedInPeriod(lowerLimit, upperLimit);
        if (filmDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filmDTOSet, HttpStatus.OK);
    }
}
