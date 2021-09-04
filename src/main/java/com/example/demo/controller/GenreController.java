package com.example.demo.controller;

import com.example.demo.model.dto.GenreDTO;
import com.example.demo.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;


    @GetMapping("/administrator/retrieveAllGenres")
    public ResponseEntity<?> retrieveAllGenres() {
        Set<GenreDTO> genreDTOSet = genreService.retrieveAllGenres();
        if (genreDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(genreDTOSet, HttpStatus.OK);
    }

    @GetMapping("/administrator/retrieveGenreByName")
    public ResponseEntity<?> retrieveGenreByName(@RequestBody String name) {
        Set<GenreDTO> genreDTOSet = genreService.retrieveGenreByName(name);
        if (genreDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(genreDTOSet, HttpStatus.OK);
    }

    @PostMapping("/administrator/createGenre")
    public ResponseEntity<?> createGenre(@RequestBody GenreDTO genreDTO) {
        GenreDTO createdGenre = genreService.createGenre(genreDTO);
        if (createdGenre == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdGenre, HttpStatus.OK);
    }

    @DeleteMapping("/administrator/deleteGenreById")
    public ResponseEntity<?> deleteGenreById(@RequestBody Integer genreId) {
        try {
            genreService.deleteGenreById(genreId);
            return new ResponseEntity<>("Genre deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Genre not found", HttpStatus.BAD_REQUEST);
        }
    }
}
