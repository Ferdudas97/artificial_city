package com.miss.artificial_city.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ChangeSimulationDetailsRequest {
    private Map<String, Integer> streamProduction;
    private Integer simulationSpeed;}

