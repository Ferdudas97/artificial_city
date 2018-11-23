package com.miss.artificial_city.model;


import com.miss.artificial_city.model.node.Node;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class Car {
    private Double acceleration;
    private Double currentSpeed;
    private Double maxSpeed;
    private List<Node> position;
}
