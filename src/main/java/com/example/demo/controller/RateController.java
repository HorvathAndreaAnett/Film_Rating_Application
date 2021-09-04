package com.example.demo.controller;

import com.example.demo.model.RateStrategyName;
import com.example.demo.model.dto.RatingDTO;
import com.example.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
public class RateController {

    @Autowired
    private RatingService ratingService;


    @PostMapping("/user/rate")
    public ResponseEntity<?> rate(@RequestParam(value = "rateStrategyName") RateStrategyName rateStrategyName, @RequestBody RatingDTO ratingDTO) {
        RatingDTO createdRating = ratingService.rate(ratingDTO, rateStrategyName);

        if (createdRating == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdRating, HttpStatus.OK);
    }

}
