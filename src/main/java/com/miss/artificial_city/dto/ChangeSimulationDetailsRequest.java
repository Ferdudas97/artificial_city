package com.miss.artificial_city.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ChangeSimulationDetailsRequest {
    private Map<String, Integer> streamProduction;
    private Integer simulationSpeed;}

