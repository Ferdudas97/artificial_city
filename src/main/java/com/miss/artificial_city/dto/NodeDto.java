package com.miss.artificial_city.dto;

import com.miss.artificial_city.model.node.NodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class NodeDto {
    //Todo: czy nie lepiej przesylac id
    private NodeType type;
    private String leftId;
    private String rightId;
    private String topId;
    private String bottomId;
    private Double maxSpeedAllowed;
    private Double verticalPosition;
    private Double horizontalPosition;
    private String nodeId;
}
