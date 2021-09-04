package com.example.demo.service;

import com.example.demo.model.dto.KeywordDTO;

import java.util.Set;

public interface KeywordService {

    Set<KeywordDTO> retrieveAllKeywords();

    Set<KeywordDTO> retrieveKeywordByName(String name);

    void deleteKeywordById(Integer id);

    KeywordDTO createKeyword(KeywordDTO keywordDTO);
}
