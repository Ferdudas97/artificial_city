package com.miss.artificial_city.infastructure.endpionts;


import com.miss.artificial_city.application.CreatorService;
import com.miss.artificial_city.dto.GetSavedBoardResponse;
import com.miss.artificial_city.dto.SaveBoardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/simulation")
public class SimulatorController {
    private final CreatorService creatorService;

    @Autowired
    public SimulatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }


    @CrossOrigin
    @PostMapping(path = "/save")
    public void saveSimulationBoard(@RequestBody SaveBoardRequest request) {
        creatorService.saveSimulationBoard(request);


    }

    @CrossOrigin
    @GetMapping(path = "/creator/{name}")
    public GetSavedBoardResponse openSimulationBoard(@PathVariable String name) {
        return creatorService.openSimulationBoard(name);
    }


    @CrossOrigin
    @GetMapping(path = "/all")
    public List<String> getAllSimulationNames() {
        return creatorService.getAllBoardNames();

    }



}
