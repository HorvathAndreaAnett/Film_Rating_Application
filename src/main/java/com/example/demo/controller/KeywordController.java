package com.example.demo.controller;

import com.example.demo.model.dto.KeywordDTO;
import com.example.demo.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/administrator/retrieveAllKeywords")
    public ResponseEntity<?> retrieveAllKeywords() {
        Set<KeywordDTO> keywordDTOSet = keywordService.retrieveAllKeywords();
        if (keywordDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(keywordDTOSet, HttpStatus.OK);
    }

    @GetMapping("/administrator/retrieveKeywordByName")
    public ResponseEntity<?> retrieveKeywordByName(@RequestBody String name) {
        Set<KeywordDTO> keywordDTOSet = keywordService.retrieveKeywordByName(name);
        if (keywordDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(keywordDTOSet, HttpStatus.OK);
    }

    @PostMapping("/administrator/createKeyword")
    public ResponseEntity<?> createKeyword(@RequestBody KeywordDTO keywordDTO) {
        KeywordDTO createdKeyword = keywordService.createKeyword(keywordDTO);
        if (createdKeyword == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdKeyword, HttpStatus.OK);
    }

    @DeleteMapping("/administrator/deleteKeywordById")
    public ResponseEntity<?> deleteKeywordById(@RequestBody Integer keywordId) {
        try {
            keywordService.deleteKeywordById(keywordId);
            return new ResponseEntity<>("Keyword deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Keyword not found", HttpStatus.BAD_REQUEST);
        }
    }
}
