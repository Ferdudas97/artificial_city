package com.miss.artificial_city.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor(staticName = "of")
@Getter
@NoArgsConstructor
@Setter
public class BoardDto {
    private String boardName;
    private Set<NodeDto> nodeDtos;
}
