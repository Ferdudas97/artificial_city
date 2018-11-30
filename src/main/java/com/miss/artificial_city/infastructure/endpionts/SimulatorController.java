package com.miss.artificial_city.infastructure.endpionts;


import com.miss.artificial_city.application.CreatorService;
import com.miss.artificial_city.application.SimulationInfo;
import com.miss.artificial_city.application.SimulationService;
import com.miss.artificial_city.dto.ChangeSimulationDetailsRequest;
import com.miss.artificial_city.dto.GetSavedBoardResponse;
import com.miss.artificial_city.dto.SaveBoardRequest;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/simulation")
public class SimulatorController {
    private final CreatorService creatorService;
    private final SimulationService simulationService;

    @Autowired
    public SimulatorController(CreatorService creatorService, SimulationService simulationService) {
        this.creatorService = creatorService;
        this.simulationService = simulationService;
    }



    @PostMapping(path = "/save")
    public void saveSimulationBoard(@RequestBody SaveBoardRequest request) {

        creatorService.saveSimulationBoard(request);

    }

    @GetMapping(path = "/open/{name}")
    public GetSavedBoardResponse openSimulationBoard(@PathVariable String name) {
        simulationService.init(name);
        return creatorService.openSimulationBoard(name);
    }

    @GetMapping(path = "/all")
    public List<String> getAllSimulationNames() {
        return creatorService.getAllBoardNames();
    }

    @PostMapping("/stop")
    public void stopSimulating() {
        simulationService.stopSimulation();
    }

    @GetMapping("/lol")
    public  String check(){
        return "LOL";
    }

    @GetMapping(path = "/positions")
    public ResponseBodyEmitter getNewCarPosition() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        service.execute(()-> {
            simulationService.startSimulation();
            while (simulationService.isSimulating()){
            try {
                emitter.send(simulationService.getNewCarPosition());
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            emitter.complete();
        });
        return emitter;
    }

    @PostMapping("/details")
    public void setNewSimulationDetails(@RequestBody ChangeSimulationDetailsRequest request) {
        val streamProductionMap = request.getStreamProduction().entrySet().stream()
                .collect(Collectors.toMap(e -> SpawnStreamId.of(e.getKey()), Map.Entry::getValue));

        simulationService.changeSimulationInfo(SimulationInfo.of(streamProductionMap,request.getSimulationSpeed()));
    }
}
