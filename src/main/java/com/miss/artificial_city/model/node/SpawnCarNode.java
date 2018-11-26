package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.car.CarFactory;
import com.miss.artificial_city.model.car.CarType;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpawnCarNode extends Node {
    private SpawnStreamId spawnStreamId;
    public Car spawnCar() {
        return CarFactory.creatorMap.get(CarType.MEDIUM).apply(this);
    }
}
