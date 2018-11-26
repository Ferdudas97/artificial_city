package com.miss.artificial_city.model.car;

import com.miss.artificial_city.model.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CarFactory {

    @FunctionalInterface
    interface CarCreator{
        Car createCar(final Node node);
    }
    public static Map<CarType, Function<Node,Car>> creatorMap;

    //Todo jakie mają miec wartości te samochody
    static {
        creatorMap = new HashMap<>();
        creatorMap.put(CarType.BIG, node -> new Car(node,4,2.0));
        creatorMap.put(CarType.MEDIUM, node -> new Car(node,2,3.0));
        creatorMap.put(CarType.SMALL, node -> new Car(node,1,4.0));

    }
}
