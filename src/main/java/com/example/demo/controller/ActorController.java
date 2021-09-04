package com.example.demo.controller;

import com.example.demo.model.dto.ActorDTO;
import com.example.demo.model.dto.RatingDTO;
import com.example.demo.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/retrieveAllActors")
    public ResponseEntity<?> retrieveAllActors() {
        Set<ActorDTO> actorDTOSet = actorService.retrieveAllActors();
        if (actorDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actorDTOSet, HttpStatus.OK);
    }


    @GetMapping("/retrieveAllActorsByName")
    public ResponseEntity<?> retrieveAllActorsByName(@RequestBody String name) {
        Set<ActorDTO> actorDTOSet = actorService.retrieveAllActorsByName(name);
        if (actorDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actorDTOSet, HttpStatus.OK);
    }


    @GetMapping("/retrieveTopActors")
    public ResponseEntity<?> retrieveTopActors(@RequestBody Integer top) {
        Set<ActorDTO> actorDTOSet = actorService.retrieveTopActors(top);
        if (actorDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actorDTOSet, HttpStatus.OK);
    }


    @PostMapping("/administrator/createActor")
    public ResponseEntity<?> createActor(@RequestBody ActorDTO actorDTO) {
        ActorDTO createdActor = actorService.createActor(actorDTO);
        if (createdActor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdActor, HttpStatus.OK);
    }


    @DeleteMapping("/administrator/deleteActorById")
    public ResponseEntity<?> deleteActorById(@RequestBody Integer actorId) {
        try {
            actorService.deleteActorById(actorId);
            return new ResponseEntity<>("Actor deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Actor not found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/retrieveAllReviewsForActor")
    public ResponseEntity<?> retrieveAllReviewsForActor(@RequestBody Integer actorId) {
        Set<RatingDTO> reviews = actorService.retrieveAllReviews(actorId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

}
