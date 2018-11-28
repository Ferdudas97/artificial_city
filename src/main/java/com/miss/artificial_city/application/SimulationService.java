package com.miss.artificial_city.application;

import com.miss.artificial_city.dto.SaveBoardRequest;
import com.miss.artificial_city.dto.SimulationResponse;

public interface SimulationService {

    void startSimulation();
    void changeSimulationInfo(SimulationInfo simulationInfo);
    void stopSimulation();
    SaveBoardRequest openSimulationBoard(String id);
    void saveSimulationBoard(SaveBoardRequest request);
    SimulationResponse getNewCarPosition();
}
