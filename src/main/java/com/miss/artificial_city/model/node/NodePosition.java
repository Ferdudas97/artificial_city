package com.miss.artificial_city.model.node;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class NodePosition {
    private final Double horiziontalPosition;
    private final Double verticalPosition;
}
