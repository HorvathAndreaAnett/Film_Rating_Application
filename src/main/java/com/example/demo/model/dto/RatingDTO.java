package com.example.demo.model.dto;

import com.example.demo.model.ActorRating;
import com.example.demo.model.FilmRating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RatingDTO {

    private Integer id;
    private Integer itemId;
    private Integer userId;
    private Integer rating;
    private String review;

    public static RatingDTO fromEntity(ActorRating actorRating) {
        return RatingDTO.builder()
                .itemId(actorRating.getActor().getId())
                .userId(actorRating.getUser().getId())
                .rating(actorRating.getRating())
                .review(actorRating.getReview())
                .build();
    }

    public static RatingDTO fromEntity(FilmRating filmRating) {
        return RatingDTO.builder()
                .itemId(filmRating.getFilm().getId())
                .userId(filmRating.getUser().getId())
                .rating(filmRating.getRating())
                .review(filmRating.getReview())
                .build();
    }

}
