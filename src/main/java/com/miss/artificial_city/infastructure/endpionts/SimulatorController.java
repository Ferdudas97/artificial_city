package com.miss.artificial_city.infastructure.endpionts;


import com.miss.artificial_city.application.CreatorService;
import com.miss.artificial_city.application.SimulationInfo;
import com.miss.artificial_city.application.SimulationService;
import com.miss.artificial_city.dto.ChangeSimulationDetailsRequest;
import com.miss.artificial_city.dto.GetSavedBoardResponse;
import com.miss.artificial_city.dto.SaveBoardRequest;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
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


    @CrossOrigin
    @PostMapping(path = "/save")
    public void saveSimulationBoard(@RequestBody SaveBoardRequest request) {
        System.out.println("WYPISZ SIE");
        creatorService.saveSimulationBoard(request);
        Logger logger = LoggerFactory.getLogger(SimulatorController.class);
        logger.debug("TTTTTTTTT {}", request);


    }

    @CrossOrigin
    @GetMapping(path = "/creator/{name}")
    public GetSavedBoardResponse openSimulationBoard(@PathVariable String name) {
        return creatorService.openSimulationBoard(name);
    }

    @CrossOrigin
    @GetMapping(path = "/{name}")
    public GetSavedBoardResponse openSimulation(@PathVariable String name) {
        simulationService.init(name);
        return creatorService.openSimulationBoard(name);
    }

    @CrossOrigin
    @GetMapping(path = "/all")
    public List<String> getAllSimulationNames() {
        return creatorService.getAllBoardNames();

    }

    @CrossOrigin
    @GetMapping("/stop")
    public void stopSimulating() {
        simulationService.stopSimulation();
    }

    @CrossOrigin
    @GetMapping("/lol")
    public String check() {
        return "LOL";
    }

    @CrossOrigin
    @GetMapping(path = "/positions")
    public ResponseBodyEmitter getNewCarPosition() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        service.execute(() -> {
            simulationService.startSimulation();
            while (simulationService.isSimulating()) {
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

    @GetMapping("/test")
    public SseEmitter streamSseMvc() {

        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event().data("SSE MVC - " + LocalTime.now().toString()).id(String.valueOf(i))
                            .name("sse event - mvc");
                    emitter.send(event);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    @CrossOrigin
    @PostMapping("/details")
    public void setNewSimulationDetails(@RequestBody ChangeSimulationDetailsRequest request) {
        val streamProductionMap = request.getStreamProduction().entrySet().stream()
                .collect(Collectors.toMap(e -> SpawnStreamId.of(e.getKey()), Map.Entry::getValue));

        simulationService.changeSimulationInfo(SimulationInfo.of(streamProductionMap, request.getSimulationSpeed()));
    }
}
