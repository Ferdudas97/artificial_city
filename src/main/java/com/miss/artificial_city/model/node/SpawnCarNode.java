package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.car.CarFactory;
import com.miss.artificial_city.model.car.CarId;
import com.miss.artificial_city.model.car.CarType;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SpawnCarNode extends Node {

    private SpawnStreamId spawnStreamId;
    public Car spawnCar() {
        return CarFactory.creatorMap.get(CarType.MEDIUM).apply(this);
    }

    @Builder
    public SpawnCarNode(@NotNull NodeType type,
                        @NotNull NodeId id,
                        @NotNull Boolean isTaken,
                        Neighbors neighbors,
                        Double maxSpeedAllowed,
                        @NotNull NodePosition position,
                        CarId carId,
                        SpawnStreamId spawnStreamId) {
        super(type, id, isTaken, neighbors, maxSpeedAllowed, position, carId);
        this.spawnStreamId = spawnStreamId;
    }
}
