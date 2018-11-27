package com.miss.artificial_city.application;

import com.miss.artificial_city.dto.SaveBoardRequest;

public interface SimulationService {

    void startSimulation();
    void changeSimulationInfo(SimulationInfo simulationInfo);
    void stopSimulation();
    SaveBoardRequest openSimulationBoard();
    void saveSimulationBoard(SaveBoardRequest request);
}
