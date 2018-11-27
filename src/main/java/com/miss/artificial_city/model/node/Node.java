package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.CarId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Builder
public class Node {
    private NodeType type;
    private NodeId id;
    private Boolean isTaken;
    private Neighbors neighbors;
    private Double maxSpeedAllowed;
    private NodePosition position;
    private CarId carId;


}
