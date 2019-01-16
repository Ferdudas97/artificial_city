package com.miss.artificial_city.dto;


import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Builder
public class NodeDto {
    private NodeType nodeType;
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
