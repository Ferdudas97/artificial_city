package com.miss.artificial_city.infastructure.endpionts;


import com.miss.artificial_city.dto.SaveBoardRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/simulation")
public class SimulatorController {


    @PostMapping(path = "/save")
    public void saveSimulationBoard(@RequestBody SaveBoardRequest request){


    }


}
