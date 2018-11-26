package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.CarId;
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
    private CarId carId;


}
