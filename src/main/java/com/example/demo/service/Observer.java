package com.example.demo.service;

import com.example.demo.model.dto.RatingDTO;

public interface Observer {

    void update(RatingDTO ratingDTO, String email);
}
