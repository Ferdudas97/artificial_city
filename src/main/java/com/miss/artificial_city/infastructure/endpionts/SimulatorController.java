package com.miss.artificial_city.infastructure.endpionts;


import com.miss.artificial_city.dto.SaveBoardRequest;
import com.miss.artificial_city.dto.SimulationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/simulation")
public class SimulatorController {


    @PostMapping(path = "/save")
    public void saveSimulationBoard(@RequestBody SaveBoardRequest request){


    }

    @GetMapping(path="open")
    public SaveBoardRequest openSimulationBoard(){
        return null;
    }

    @GetMapping(path = "get-new-car-position")
    public SimulationResponse getNewCarPosition(){
        return null;
    }


}
