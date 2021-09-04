package com.example.demo.service;

import com.example.demo.model.RateStrategyName;
import com.example.demo.model.User;
import com.example.demo.model.dto.RatingDTO;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RatingServiceImplementation implements RatingService, Subject {

    @Autowired
    private RateStrategyFactory rateStrategyFactory;

    @Autowired
    private UserRepository userRepository;

    private ArrayList<Observer> observers = new ArrayList<>();
    private Observer loggingObserver = new LoggingService();
    private Observer emailObserver = new EmailService();

    public RatingServiceImplementation() {
        registerObserver(loggingObserver);
        registerObserver(emailObserver);
    }

    @Override
    public RatingDTO rate(RatingDTO ratingDTO, RateStrategyName strategyName) {
        RateStrategy rateStrategy = rateStrategyFactory.findStrategy(strategyName);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        User loggedUser = userRepository.findByUsername(authentication.getName());
        ratingDTO.setUserId(loggedUser.getId());

        RatingDTO createdRating = rateStrategy.rate(ratingDTO);
        notifyObservers(createdRating, loggedUser.getEmail());

        return createdRating;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(RatingDTO ratingDTO, String email) {
        for (Observer observer: observers) {
            observer.update(ratingDTO, email);
        }
    }
}
