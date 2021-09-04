package com.example.demo.model.dto;

import com.example.demo.model.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class KeywordDTO {

    private Integer id;
    private String name;
    private Set<Integer> filmIdSet;

    public static KeywordDTO fromEntity(Keyword keyword) {
        return KeywordDTO.builder()
                .id(keyword.getId())
                .name(keyword.getName())
                .build();
    }
}
