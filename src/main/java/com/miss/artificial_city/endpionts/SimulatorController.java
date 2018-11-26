package com.miss.artificial_city.endpionts;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/simulation")
public class SimulatorController {


    @PostMapping(path = "/simulation")
    public String saveSimulationMap(){
        return "lol";
    }


}
