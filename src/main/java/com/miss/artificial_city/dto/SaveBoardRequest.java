package com.miss.artificial_city.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SaveBoardRequest {
    private String simulationName;
    private List<NodeDto>  nodeDtos;
}
