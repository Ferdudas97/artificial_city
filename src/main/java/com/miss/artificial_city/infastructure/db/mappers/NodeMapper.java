package com.miss.artificial_city.infastructure.db.mappers;

import com.miss.artificial_city.dto.NodeDto;
import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import com.miss.artificial_city.infastructure.db.entities.NodeEntity;
import com.miss.artificial_city.model.node.Neighbors;
import com.miss.artificial_city.model.node.Node;
import com.miss.artificial_city.model.node.NodeId;
import com.miss.artificial_city.model.node.NodePosition;
import lombok.val;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NodeMapper {
    public static List<Node> toDomain(final List<NodeEntity> entities) {
        val mapWithNodes =
                entities.stream().map(NodeMapper::toDomain)
                        .collect(Collectors.toMap(e -> e.getId().getId(), Function.identity()));

        entities.forEach(entity -> setNeighbours(entity, mapWithNodes));
        return new ArrayList<>(mapWithNodes.values());
    }

    public static Set<NodeEntity> toEntity(final Set<NodeDto> dtos, final BoardEntity boardEntity) {
        return dtos.stream()
                .map(dto -> toEntity(dto, boardEntity))
                .collect(Collectors.toSet());
    }

    public static Set<NodeDto> toDto(final Set<NodeEntity> entities) {

        return entities.stream()
                .map(NodeMapper::toDto)
                .collect(Collectors.toSet());
    }

    private static NodeDto toDto(final NodeEntity entity) {
        return NodeDto.builder()
                .type(entity.getNodeType())
                .nodeId(entity.getNodeId())
                .bottomId(entity.getBottomNodeId())
                .leftId(entity.getLeftNodeId())
                .topId(entity.getTopNodeId())
                .rightId(entity.getRightNodeId())
                .horizontalPosition(entity.getHorizontalPosition())
                .verticalPosition(entity.getVerticalPosition())
                .maxSpeedAllowed(entity.getMaxSpeedAllowed())
                .build();
    }

    private static NodeEntity toEntity(final NodeDto dto, final BoardEntity board) {
        return NodeEntity.builder()
                .id(UUID.randomUUID().toString())
                .boardEntity(board)
                .nodeId(dto.getNodeId())
                .nodeType(dto.getType())
                .verticalPosition(dto.getVerticalPosition())
                .horizontalPosition(dto.getHorizontalPosition())
                .maxSpeedAllowed(dto.getMaxSpeedAllowed())
                .bottomNodeId(dto.getBottomId())
                .rightNodeId(dto.getRightId())
                .topNodeId(dto.getTopId())
                .leftNodeId(dto.getLeftId())
                .build();
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
