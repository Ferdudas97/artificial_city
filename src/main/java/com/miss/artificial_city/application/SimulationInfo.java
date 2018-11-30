package com.miss.artificial_city.application;

import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;


@Getter
@AllArgsConstructor(staticName = "of")
public class SimulationInfo {
    // ile dany spawnStream ma produkować na minute
    private Map<SpawnStreamId, Integer> streamProduction;
    private Integer simulationSpeed;
}
