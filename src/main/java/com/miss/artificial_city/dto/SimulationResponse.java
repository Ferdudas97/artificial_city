package com.miss.artificial_city.dto;

import com.miss.artificial_city.model.node.NodePosition;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor(staticName = "of")
public class SimulationResponse {
    //Todo Map<CarId,List<NodeId>> tak czy tak
    public Map<String, NodePosition> response;
}
