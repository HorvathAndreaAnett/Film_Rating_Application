package com.example.demo.model.dto;

import com.example.demo.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class GenreDTO {

    private Integer id;
    private String name;
    private Set<Integer> filmIdSet;

    public static GenreDTO fromEntity(Genre genre) {
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }


}
