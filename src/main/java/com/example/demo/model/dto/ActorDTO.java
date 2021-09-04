package com.example.demo.model.dto;


import com.example.demo.model.Actor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class ActorDTO {

    private Integer id;
    private String name;
    private Float rating;

    public static ActorDTO fromEntity(Actor actor) {
        return ActorDTO.builder()
                .id(actor.getId())
                .name(actor.getName())
                .rating(actor.getRating())
                //filmSet
                .build();
    }

}
