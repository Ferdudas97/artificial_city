package com.miss.artificial_city.model.car;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


@Getter
public class CarHolder {
    public static final Map<CarId,Car> cars= new HashMap<>();

    public static void addCar(final Car car) {
        cars.put(car.getId(), car);
    }

    public static void removeCar(final Car car) {
        cars.remove(car.getId());
    }
    public static Stream<Car> getAllCars(){
        return cars.values().stream();
    }
}
