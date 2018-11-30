package com.miss.artificial_city.application;

import com.miss.artificial_city.dto.SimulationResponse;

public interface SimulationService {

    void startSimulation();
    void changeSimulationInfo(SimulationInfo simulationInfo);
    void stopSimulation();
    void init(String name);
    SimulationResponse getNewCarPosition();
    boolean isSimulating();

}
