package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.car.CarFactory;
import com.miss.artificial_city.model.car.CarType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpawnCarNode extends Node {

    public Car spawnCar() {
        return CarFactory.creatorMap.get(CarType.MEDIUM).apply(this);
    }
}
