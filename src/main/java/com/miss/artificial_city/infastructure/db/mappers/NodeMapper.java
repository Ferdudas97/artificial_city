package com.miss.artificial_city.infastructure.db.mappers;

import com.miss.artificial_city.infastructure.db.entities.NodeEntity;
import com.miss.artificial_city.model.node.Neighbors;
import com.miss.artificial_city.model.node.Node;
import com.miss.artificial_city.model.node.NodeId;
import com.miss.artificial_city.model.node.NodePosition;
import lombok.val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NodeMapper {
    public static List<Node> toDomain(final List<NodeEntity> entities) {
        val mapWithNodes =
                entities.stream().map(NodeMapper::toDomain)
                        .collect(Collectors.toMap(e -> e.getId().getId(), Function.identity()));

        entities.forEach(entity ->
                setNeighbours(entity, mapWithNodes));
        return new ArrayList<>(mapWithNodes.values());
    }
    
    private static Node toDomain(final NodeEntity entity) {
        return Node.builder()
                .id(NodeId.of(entity.getNodeId()))
                .type(entity.getNodeType())
                .maxSpeedAllowed(entity.getMaxSpeedAllowed())
                .position(NodePosition.of(entity.getHorizontalPosition(), entity.getVerticalPosition()))
                .build();
    }

    private static void setNeighbours(final NodeEntity entity, final Map<String, Node> map) {
        map.get(entity.getId())
                .setNeighbors(Neighbors.of(map.get(entity.getTopNodeId()),
                        map.get(entity.getLeftNodeId()),
                        map.get(entity.getRightNodeId()),
                        map.get(entity.getBottomNodeId())));
    }
}
