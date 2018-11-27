package com.miss.artificial_city.dto;

import com.miss.artificial_city.model.node.Node;
import com.miss.artificial_city.model.node.NodeType;

public class NodeDto {
    //Todo: czy nie lepiej przesylac id
    private NodeType type;
    private String leftId;
    private String rightId;
    private String topId;
    private String bottomId;
    private Double verticalPosition;
    private Double horizontalPosition;
    private String nodeId;
}
