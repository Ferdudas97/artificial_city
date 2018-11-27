package com.miss.artificial_city.application;

import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.car.CarHolder;
import com.miss.artificial_city.model.node.spawn.CarSpawner;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lombok.val;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SimulationServiceImpl implements SimulationService {
    private boolean isSimulating;
    private SimulationInfo simulationInfo = new SimulationInfo();
    private List<Disposable> spawnDisposables;
    private List<Disposable> traffigLightsDisposables;

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

    private void spawnCars(final SpawnStreamId id) {
        val car = CarSpawner.spawnCar(id);
        CarHolder.addCar(car);
    }
}
