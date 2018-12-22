package com.miss.artificial_city.infastructure.db.entities;

import com.miss.artificial_city.model.node.NodeDirection;
import com.miss.artificial_city.model.node.NodeType;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "NODE_TABLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NodeEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOARD_ID")
    private BoardEntity board;

    @Column(name = "NODE_ID",nullable = false)
    private String nodeId;

    @Column(name = "SPAWN_STREAM_ID")
    private String spawnStreamId;

    @Column(name = "HORIZONTAL_POSITION",nullable = false)
    private Double horizontalPosition;

    @Column(name = "VERTICAL_POSITION",nullable = false)
    private Double verticalPosition;

    @Enumerated(EnumType.STRING)
    private NodeType nodeType;

    @Enumerated(EnumType.STRING)
    private NodeDirection direction;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeEntity that = (NodeEntity) o;
        return
                Objects.equals(nodeId, that.nodeId) &&
                Objects.equals(spawnStreamId, that.spawnStreamId) &&
                Objects.equals(horizontalPosition, that.horizontalPosition) &&
                Objects.equals(verticalPosition, that.verticalPosition) &&
                nodeType == that.nodeType &&
                Objects.equals(maxSpeedAllowed, that.maxSpeedAllowed) &&
                Objects.equals(leftNodeId, that.leftNodeId) &&
                Objects.equals(rightNodeId, that.rightNodeId) &&
                Objects.equals(topNodeId, that.topNodeId) &&
                Objects.equals(bottomNodeId, that.bottomNodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, spawnStreamId, horizontalPosition, verticalPosition, nodeType, maxSpeedAllowed, leftNodeId, rightNodeId, topNodeId, bottomNodeId);
    }
}
