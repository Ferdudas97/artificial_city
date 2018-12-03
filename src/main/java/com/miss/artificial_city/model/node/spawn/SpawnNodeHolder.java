package com.miss.artificial_city.model.node.spawn;

import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.node.SpawnCarNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpawnNodeHolder {
    public static Map<SpawnStreamId, SpawnStream> spawnStreams  = new HashMap<>();;

    public static Car spawnCar(final SpawnStreamId id) {
        return spawnStreams.get(id).spawnCar();
    }

    public static void addToSpawnStrem(SpawnCarNode spawnCarNode) {
        spawnStreams.putIfAbsent(spawnCarNode.getSpawnStreamId(),
                SpawnStream.of(spawnCarNode.getSpawnStreamId(), new ArrayList<>()));
        spawnStreams.get(spawnCarNode.getSpawnStreamId())
                .getNodes()
                .add(spawnCarNode);
    }
}
