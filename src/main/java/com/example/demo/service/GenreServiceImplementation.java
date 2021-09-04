package com.example.demo.service;

import com.example.demo.model.Genre;
import com.example.demo.model.dto.GenreDTO;
import com.example.demo.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreServiceImplementation implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Set<GenreDTO> retrieveAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(GenreDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<GenreDTO> retrieveGenreByName(String name) {
        return genreRepository.findByName(name)
                .stream()
                .map(GenreDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteGenreById(Integer id) {
        genreRepository.deleteById(id);
    }

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
        if (!isGenreValid(genreDTO)) {
            return null;
        }
        Genre genre = modelMapper.map(genreDTO, Genre.class);
        genre = genreRepository.save(genre);
        return GenreDTO.fromEntity(genre);
    }

    private boolean isGenreValid(GenreDTO genreDTO) {
        if (genreDTO.getName().matches("[a-z A-Z]+")) {
            return true;
        }
        return false;
    }
}
