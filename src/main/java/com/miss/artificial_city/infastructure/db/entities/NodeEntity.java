package com.miss.artificial_city.infastructure.db.entities;

import com.miss.artificial_city.model.node.NodeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "NODE_ENTITY")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class NodeEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOARD_ID")
    private BoardEntity boardEntity;

    @Column(name = "NODE_ID",nullable = false)
    private String nodeId;

    @Column(name = "HORIZONTAL_POSITION")
    private Double horizontalPosition;

    @Column(name = "VERTICAL_POSITION")
    private Double verticalPosition;

    @Enumerated(EnumType.STRING)
    private NodeType nodeType;

    @Column(name = "MAX_SPEED")
    private Double maxSpeedAllowed;

    //Todo trzymac id czy dacj one to one?
    @Column(name = "LEFT_NODE_ID")
    private String leftNodeId;

    @Column(name = "RIGHT_NODE_ID")
    private String rightNodeId;

    @Column(name = "TOP_NODE_ID")
    private String topNodeId;

    @Column(name = "BOTTOM_NODE_ID")
    private String bottomNodeId;

    public NodeEntity(String id, BoardEntity boardEntity, String nodeId, Double horizontalPosition, Double verticalPosition, NodeType nodeType, Double maxSpeedAllowed, String leftNodeId, String rightNodeId, String topNodeId, String bottomNodeId) {
        this.id = id;
        this.boardEntity = boardEntity;
        this.nodeId = nodeId;
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.nodeType = nodeType;
        this.maxSpeedAllowed = maxSpeedAllowed;
        this.leftNodeId = leftNodeId;
        this.rightNodeId = rightNodeId;
        this.topNodeId = topNodeId;
        this.bottomNodeId = bottomNodeId;
    }
}
