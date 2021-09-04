package com.example.demo.service;

import com.example.demo.model.RateStrategyName;
import com.example.demo.model.dto.RatingDTO;


public interface RateStrategy {

    RatingDTO rate(RatingDTO ratingDTO);

    RateStrategyName getRateStrategyName();
}
