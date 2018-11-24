package com.miss.artificial_city.model.car;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
public class CarHolder {
    public static final Map<CarId,Car> cars= new HashMap<>();

}
