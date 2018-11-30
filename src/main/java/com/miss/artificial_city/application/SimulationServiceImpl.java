package com.miss.artificial_city.application;

import com.miss.artificial_city.dto.SimulationResponse;
import com.miss.artificial_city.infastructure.db.mappers.NodeMapper;
import com.miss.artificial_city.infastructure.db.repositories.BoardDao;
import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.car.CarHolder;
import com.miss.artificial_city.model.node.Node;
import com.miss.artificial_city.model.node.NodePosition;
import com.miss.artificial_city.model.node.NodeType;
import com.miss.artificial_city.model.node.SpawnCarNode;
import com.miss.artificial_city.model.node.spawn.SpawnNodeHolder;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class SimulationServiceImpl implements SimulationService {
    private boolean isSimulating;
    private SimulationInfo simulationInfo;
    private List<Disposable> spawnDisposables;
    private List<Disposable> traffigLightsDisposables;
    private final BoardDao boardDao;

    @Autowired
    public SimulationServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

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

    private void setSpawnStreams() {
        Function<Map.Entry<SpawnStreamId, Integer>, Observable<Long>> mapToObesrvable;
        mapToObesrvable = entry -> Observable.interval(entry.getValue() / 60 / simulationInfo.getSimulationSpeed(),
                TimeUnit.SECONDS);
        // mały potworek XD aby  tworzyc samochód, i dodawać go do holdera
        if (spawnDisposables != null) spawnDisposables.forEach(Disposable::dispose);
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
    public void init(String name) {
        getNodes(name);
    }


    private void  getNodes(final String name) {
       val entities = boardDao.findByName(name).getNodeEntities();
       val nodes = NodeMapper.toDomain(entities);
       nodes.stream()
               .filter(node -> NodeType.SPAWN.equals(node.getType()))
               .forEach(node -> {
                   val spawnNode = (SpawnCarNode) node;
                   SpawnNodeHolder.addToSpawnStrem(spawnNode);
               });
    }



    private void spawnCars(final SpawnStreamId id) {
        val car = SpawnNodeHolder.spawnCar(id);
        CarHolder.addCar(car);
    }

    @Override
    public SimulationResponse getNewCarPosition() {
        val mapOfPostions = CarHolder.getAllCars()
                .collect(Collectors.toMap(e -> e.getId().getId(), this::averagePosition));
        return SimulationResponse.of(mapOfPostions);

    }

    @Override
    public boolean isSimulating() {
        return isSimulating;
    }


    //Todo to powinno byc w klasie Car
    private NodePosition averagePosition(final Car car) {
        Set<NodePosition> nodePositions = new HashSet<>();
        int size = car.getSize();
        Node node = car.getHead();
        for (int i = 0; i < size; i++) {
            nodePositions.add(node.getPosition());
            node = node.getNeighbors().getRight();
        }
        nodePositions.stream().mapToDouble(NodePosition::getHoriziontalPosition).average();
        nodePositions.stream().mapToDouble(NodePosition::getHoriziontalPosition).average();
        //TODO 
        return null;

    }
}
