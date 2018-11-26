package com.miss.artificial_city.application;

import com.miss.artificial_city.model.car.Car;
import com.miss.artificial_city.model.car.CarHolder;
import com.miss.artificial_city.model.node.SpawnCarNode;
import com.miss.artificial_city.model.node.spawn.CarSpawner;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;

import java.util.Collections;
import java.util.stream.Collectors;

public class SimulationServiceImpl implements SimulationService {
    private boolean isSimulating;

    @Override
    public void startSimulation() {
        isSimulating = true;
        while (isSimulating) {
            CarHolder.getAllCars().forEach(Car::move);
            
        }
    }

    @Override
    public void setTimeStep(Double timeStep) {

    }

    @Override
    public void stopSimulaion() {
        isSimulating = false;
    }

    private void spawnCars(final SpawnStreamId id, final int howmany) {
        CarSpawner.spawnCars(id, howmany)
                .forEach(CarHolder::addCar);
    }
}
