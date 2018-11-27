package com.miss.artificial_city.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor(staticName = "of")
@Getter
public class BoardDto {
    private String boardName;
    private Set<NodeDto> nodeDtos;
}
