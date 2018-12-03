package com.miss.artificial_city.dto;

import com.miss.artificial_city.model.node.NodeType;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class NodeDto {
    private NodeType type;
    private String leftId;
    private String rightId;
    private String topId;
    private String bottomId;
    //todo czy to dać tu czy tworzyc nowy dto?
    private String spawnStreamId;
    private Double maxSpeedAllowed;
    private Double verticalPosition;
    private Double horizontalPosition;
    private String nodeId;
}
