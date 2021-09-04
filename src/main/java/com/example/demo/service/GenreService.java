package com.example.demo.service;

import com.example.demo.model.dto.GenreDTO;

import java.util.Set;

public interface GenreService {

    Set<GenreDTO> retrieveAllGenres();

    Set<GenreDTO> retrieveGenreByName(String name);

    void deleteGenreById(Integer id);

    GenreDTO createGenre(GenreDTO genreDTO);

}
