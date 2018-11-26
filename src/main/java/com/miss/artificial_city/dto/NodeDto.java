package com.miss.artificial_city.dto;

import com.miss.artificial_city.model.node.Node;
import com.miss.artificial_city.model.node.NodeType;

public class NodeDto {
    //Todo: czy nie lepiej przesylac id
    private NodeType type;
    private NodeDto left;
    private NodeDto right;
    private NodeDto top;
    private NodeDto bottom;
    //Todo czy będziemy przesyłać jakies zdjecia?
    private Double verticalPosition;
    private Double horizontalPosition;
    private String nodeId;
}
