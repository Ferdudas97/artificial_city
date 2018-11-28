package com.miss.artificial_city.infastructure.endpionts;


import com.miss.artificial_city.application.SimulationService;
import com.miss.artificial_city.dto.SaveBoardRequest;
import com.miss.artificial_city.dto.SimulationResponse;
import org.springframework.web.bind.annotation.*;

@RestController("/simulation")
public class SimulatorController {
    SimulationService simulationService;


    @PostMapping(path = "/save")
    public void saveSimulationBoard(@RequestBody SaveBoardRequest request) {

        simulationService.saveSimulationBoard(request);

    }

    @GetMapping(path = "open/{id}")
    public SaveBoardRequest openSimulationBoard(@PathVariable String id) {

        return simulationService.openSimulationBoard(id);

    }

    @GetMapping(path = "updateCarPosition")
    public SimulationResponse getNewCarPosition() {
        return simulationService.getNewCarPosition();
    }


}
