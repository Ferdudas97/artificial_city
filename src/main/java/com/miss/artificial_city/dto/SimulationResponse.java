package com.miss.artificial_city.dto;

import com.miss.artificial_city.model.node.NodePosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor(staticName = "of")
public class SimulationResponse {
    //Todo Map<CarId,List<NodeId>> tak czy tak
    public Map<String, NodePosition> response;
}
