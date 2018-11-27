package com.miss.artificial_city.model.node.spawn;

import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.node.SpawnCarNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(staticName = "of")
@Getter
public class SpawnStream {
    private final SpawnStreamId id;
    private final List<SpawnCarNode> nodes;
    public Car spawnCar() {
        return nodes.stream().filter(node -> !node.getIsTaken())
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                    Collections.shuffle(collected);
                    return collected.stream();
                }))
                .limit(1)
                .map(SpawnCarNode::spawnCar)
                .findFirst()
                .orElse(null);
    }


}
