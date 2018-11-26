package com.miss.artificial_city.model.node.spawn;

import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.node.spawn.SpawnStream;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;

import java.util.Map;
import java.util.stream.Stream;

public class CarSpawner {
    public static Map<SpawnStreamId, SpawnStream> spawnStreams;

    public static Stream<Car> spawnCars(final SpawnStreamId id, final int howmany) {
        return spawnStreams.get(id).spawnCars(howmany);
    }
}
