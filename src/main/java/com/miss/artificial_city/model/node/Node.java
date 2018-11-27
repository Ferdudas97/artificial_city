package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.CarId;
import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Node {
    @NotNull
    private NodeType type;

    @NotNull
    private NodeId id;

    @NotNull
    private Boolean isTaken;

    private Neighbors neighbors;

    private Double maxSpeedAllowed;

    @NotNull
    private NodePosition position;

    private CarId carId;


}
