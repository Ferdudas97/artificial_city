package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.CarId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class Node {
    private NodeType type;
    private Boolean isTaken;
    private Neighbors neighbors;
    private Double maxSpeedAllowed;
    private CarId carId;


}
