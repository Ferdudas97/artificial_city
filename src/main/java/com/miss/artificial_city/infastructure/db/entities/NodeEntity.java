package com.miss.artificial_city.infastructure.db.entities;

import com.miss.artificial_city.model.node.NodeType;
import lombok.*;

import javax.persistence.*;

@Entity(name = "NODE_ENTITY")
@Getter
@Setter
@NoArgsConstructor
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


}
