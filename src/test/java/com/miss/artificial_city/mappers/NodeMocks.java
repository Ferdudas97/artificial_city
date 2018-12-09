package com.miss.artificial_city.mappers;

import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import com.miss.artificial_city.infastructure.db.entities.NodeEntity;
import com.miss.artificial_city.model.node.NodeDirection;
import com.miss.artificial_city.model.node.NodeType;
import lombok.val;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NodeMocks {

    public static BoardEntity mockBoardEntity() {
        val boardEntity = BoardEntity.builder()
                .id("lol")
                .name("test")
                .build();
        val nodeEntity0 = NodeEntity.builder()
                .nodeType(NodeType.SPAWN)
                .id("SPAWN NODE 0")
                .nodeId("0")
                .leftNodeId(null)
                .rightNodeId("1")
                .topNodeId("2")
                .bottomNodeId("3")
                .horizontalPosition(1.0)
                .verticalPosition(2.0)
                .board(boardEntity)
                .direction(NodeDirection.RIGHT)
                .build();
        val nodeEntity1 = NodeEntity.builder()
                .nodeType(NodeType.ROAD)
                .id("NODE1")
                .nodeId("1")
                .leftNodeId("0")
                .rightNodeId(null)
                .topNodeId(null)
                .bottomNodeId(null)
                .horizontalPosition(3.0)
                .verticalPosition(4.0)
                .board(boardEntity)
                .direction(NodeDirection.RIGHT)
                .build();
        val nodeEntity2 = NodeEntity.builder()
                .nodeType(NodeType.ROAD)
                .id("NODE2")
                .nodeId("2")
                .leftNodeId(null)
                .rightNodeId(null)
                .topNodeId(null)
                .bottomNodeId("1")
                .horizontalPosition(6.0)
                .verticalPosition(4.0)
                .board(boardEntity)
                .direction(NodeDirection.RIGHT)
                .build();
        val nodeEntity3 = NodeEntity.builder()
                .nodeType(NodeType.ROAD)
                .id("NODE3")
                .nodeId("3")
                .leftNodeId(null)
                .rightNodeId(null)
                .topNodeId("0")
                .bottomNodeId(null)
                .horizontalPosition(3.0)
                .verticalPosition(4.0)
                .board(boardEntity)
                .direction(NodeDirection.RIGHT)
                .build();
        Set<NodeEntity> nodeEntities = Stream.of(nodeEntity0, nodeEntity1, nodeEntity2, nodeEntity3)
                .collect(Collectors.toSet());
        boardEntity.setNodeEntities(nodeEntities);
        return boardEntity;
    }
}
