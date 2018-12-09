package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.CarId;
import lombok.Builder;

import javax.validation.constraints.NotNull;

public class RoadNode extends Node {

    @Builder
    public RoadNode(@NotNull NodeType type,
                    @NotNull NodeId id,
                    @NotNull Boolean isTaken,
                    Neighbors neighbors,
                    Double maxSpeedAllowed,
                    @NotNull NodePosition position,
                    CarId carId,
                    @NotNull NodeDirection direction) {

        super(type, id, isTaken, neighbors, maxSpeedAllowed, position, carId, direction);
    }
}
