package com.miss.artificial_city.dto;

import com.miss.artificial_city.model.node.NodeDirection;
import com.miss.artificial_city.model.node.NodeType;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class NodeDto {
    private NodeType type;
    private NodeDirection direction;
    private String leftId;
    private String rightId;
    private String topId;
    private String bottomId;
    private String spawnStreamId; // czy to dostane?
    private Double maxSpeedAllowed;
    private Double verticalPosition;
    private Double horizontalPosition;
    private String nodeId;
}
