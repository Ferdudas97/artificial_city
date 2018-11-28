package com.miss.artificial_city.application;

import com.miss.artificial_city.dto.NodeDto;
import com.miss.artificial_city.dto.SaveBoardRequest;
import com.miss.artificial_city.dto.SimulationResponse;
import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import com.miss.artificial_city.infastructure.db.entities.NodeEntity;
import com.miss.artificial_city.infastructure.db.mappers.NodeMapper;
import com.miss.artificial_city.infastructure.repository.BoardRepository;
import com.miss.artificial_city.infastructure.repository.NodeRepository;
import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.car.CarHolder;
import com.miss.artificial_city.model.node.Node;
import com.miss.artificial_city.model.node.NodePosition;
import com.miss.artificial_city.model.node.spawn.CarSpawner;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import com.sun.xml.internal.bind.v2.TODO;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lombok.val;
import lombok.var;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SimulationServiceImpl implements SimulationService {
    private boolean isSimulating;
    private SimulationInfo simulationInfo = new SimulationInfo();
    private List<Disposable> spawnDisposables;
    private List<Disposable> traffigLightsDisposables;
    private NodeRepository nodeRepository;
    private BoardRepository boardRepository;

    @Override
    public void startSimulation() {
        isSimulating = true;
        setSpawnStreams();
        while (isSimulating) {
            CarHolder.getAllCars().forEach(Car::move);

        }

    }

    @Override
    public void changeSimulationInfo(final SimulationInfo simulationInfo) {
        this.simulationInfo = simulationInfo;
        setSpawnStreams();
    }

    public static void main(String[] arg) {
    }

    private void setSpawnStreams() {
        Function<Map.Entry<SpawnStreamId, Integer>, Observable<Long>> mapToObesrvable;
        mapToObesrvable = entry -> Observable.interval(entry.getValue() / 60 / simulationInfo.getSimulationSpeed(),
                TimeUnit.SECONDS);
        // mały potworek XD aby  tworzyc samochód, i dodawać go do holdera
        if (spawnDisposables!=null) spawnDisposables.forEach(Disposable::dispose);
        this.spawnDisposables = simulationInfo.getStreamProduction().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, mapToObesrvable))
                .entrySet()
                .stream()
                .map(entry -> entry.getValue().subscribe(aLong -> spawnCars(entry.getKey())))
                .collect(Collectors.toList());


    }



    @Override
    public void stopSimulation() {

        isSimulating = false;
        spawnDisposables.forEach(Disposable::dispose);
    }

    @Override
    public SaveBoardRequest openSimulationBoard(String id) {
       var boardEntity=boardRepository.findOne(id);
       return new SaveBoardRequest(boardEntity.getName(),NodeMapper.toDto(boardEntity.getNodeEntities()));
    }

    @Override
    public void saveSimulationBoard(SaveBoardRequest request) {

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setName(request.getSimulationName());
        boardEntity.setNodeEntities(NodeMapper.toEntity(request.getNodeDtos(),boardEntity));
        boardRepository.save(boardEntity);
        //TODO fajnie by było tak, ale nie mam pomysłu jak dać boardEnrity do mappera
//        this.boardRepository.save(BoardEntity.builder().name(request.getSimulationName())
//                .nodeEntities(NodeMapper.toEntity(request.getNodeDtos(),boardEntity)).build());


    }

    private void spawnCars(final SpawnStreamId id) {
        val car = CarSpawner.spawnCar(id);
        CarHolder.addCar(car);
    }

    @Override
    public SimulationResponse getNewCarPosition() {
        SimulationResponse simulationResponse = new SimulationResponse();
        Set<Car> setCar = new HashSet<>(); // TODO tylko do testów
        setCar.forEach(elem->{
            simulationResponse.response.put(elem.getId().toString(),averagePosition(elem.getSize(),elem.getHead()));
        });

        return simulationResponse;

    }



    private NodePosition averagePosition(int size, Node node){
        Set<NodePosition> nodePositions = new HashSet<>();
        for (int i = 0; i < size; i++) {
            nodePositions.add(node.getPosition());
            node=node.getNeighbors().getRight();
        }
        nodePositions.stream().mapToDouble(NodePosition::getHoriziontalPosition).average();
        nodePositions.stream().mapToDouble(NodePosition::getHoriziontalPosition).average();
        //TODO 
        return null;

    }
}
