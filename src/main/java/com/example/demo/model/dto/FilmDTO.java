package com.example.demo.model.dto;

import com.example.demo.model.Actor;
import com.example.demo.model.Film;
import com.example.demo.model.Genre;
import com.example.demo.model.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class FilmDTO {

    private Integer id;
    private String name;
    private Float rating;
    private Set<Integer> genreIdSet;
    private Set<Integer> keywordIdSet;
    private Integer runtime;
    private Date releaseDate;
    private Set<Integer> actorIdSet;

    public static FilmDTO fromEntity(Film film) {

        return FilmDTO.builder()
                .id(film.getId())
                .name(film.getName())
                .rating(film.getRating())
                .genreIdSet(film.getGenreSet().stream().map(Genre::getId).collect(Collectors.toSet()))
                .keywordIdSet(film.getKeywordSet().stream().map(Keyword::getId).collect(Collectors.toSet()))
                .runtime(film.getRuntime())
                .releaseDate(film.getReleaseDate())
                .actorIdSet(film.getCast().stream().map(Actor::getId).collect(Collectors.toSet()))
                .build();
    }


}
