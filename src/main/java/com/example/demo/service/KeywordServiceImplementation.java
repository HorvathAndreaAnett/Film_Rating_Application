package com.example.demo.service;

import com.example.demo.model.Keyword;
import com.example.demo.model.dto.KeywordDTO;
import com.example.demo.repository.KeywordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class KeywordServiceImplementation implements KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Set<KeywordDTO> retrieveAllKeywords() {
        return keywordRepository.findAll()
                .stream()
                .map(KeywordDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<KeywordDTO> retrieveKeywordByName(String name) {
        return keywordRepository.findByName(name)
                .stream()
                .map(KeywordDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteKeywordById(Integer id) {
        keywordRepository.deleteById(id);
    }

    @Override
    public KeywordDTO createKeyword(KeywordDTO keywordDTO) {
        if (!isKeywordValid(keywordDTO)) {
            return null;
        }
        Keyword keyword = modelMapper.map(keywordDTO, Keyword.class);
        keyword = keywordRepository.save(keyword);
        return KeywordDTO.fromEntity(keyword);
    }

    private boolean isKeywordValid(KeywordDTO keywordDTO) {
        if (keywordDTO.getName().matches("[a-z A-Z]+")) {
            return true;
        }
        return false;
    }
}
