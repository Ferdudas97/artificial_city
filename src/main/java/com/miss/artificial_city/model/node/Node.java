package com.miss.artificial_city.model.node;

import jdk.nashorn.internal.runtime.regexp.joni.constants.NodeType;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Node {
    private NodeType type;
    private String id;
    private Boolean isTaken;
    private Neighbors neighbors;
    private Double maxSpeedAllowed;


}
