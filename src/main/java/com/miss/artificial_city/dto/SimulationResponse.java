package com.miss.artificial_city.dto;

import com.miss.artificial_city.model.car.CarId;

import java.util.List;
import java.util.Map;

public class SimulationResponse {
    //Todo Map<CarId,List<NodeId>> tak czy tak
    public Map<String, List<String>> response;
}
