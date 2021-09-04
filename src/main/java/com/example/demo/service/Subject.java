package com.example.demo.service;

import com.example.demo.model.dto.RatingDTO;

public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(RatingDTO ratingDTO, String email);

}
