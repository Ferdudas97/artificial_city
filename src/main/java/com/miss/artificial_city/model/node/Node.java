package com.miss.artificial_city.model.node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class Node {
    private final String id;
    private  Boolean isTaken;
    private  Neighbors neighbors;


}
