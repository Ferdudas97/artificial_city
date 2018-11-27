package com.miss.artificial_city.model.node.spawn;

import com.miss.artificial_city.model.car.Car;

import java.util.Map;

public class CarSpawner {
    public static Map<SpawnStreamId, SpawnStream> spawnStreams;

    public static Car spawnCar(final SpawnStreamId id) {
        return spawnStreams.get(id).spawnCar();
    }
}
