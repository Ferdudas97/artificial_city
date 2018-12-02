package com.miss.artificial_city.infastructure.db.mappers;

import com.miss.artificial_city.dto.NodeDto;
import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import com.miss.artificial_city.infastructure.db.entities.NodeEntity;
import com.miss.artificial_city.model.node.*;
import com.miss.artificial_city.model.node.spawn.SpawnStreamId;
import lombok.val;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NodeMapper {
    private static Map<NodeType, Function<NodeEntity, Node>> specifiedMappers;

    static {
        specifiedMappers = new HashMap<>();
        specifiedMappers.putIfAbsent(NodeType.ROAD, NodeMapper::toRoadNode);
        specifiedMappers.putIfAbsent(NodeType.SPAWN, NodeMapper::toSpawnNode);
    }

    public static Set<Node> toDomain(final Set<NodeEntity> entities) {
        val mapWithNodes =
                entities.stream().map(entity -> specifiedMappers.get(entity.getNodeType()).apply(entity))
                        .collect(Collectors.toMap(e -> e.getId().getId(), Function.identity()));

        entities.forEach(entity -> setNeighbours(entity, mapWithNodes));
        return new HashSet<>(mapWithNodes.values());
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
                .spawnStreamId(entity.getSpawnStreamId())
                .build();
    }

    private static NodeEntity toEntity(final NodeDto dto, final BoardEntity board) {
        return NodeEntity.builder()
                .id(UUID.randomUUID().toString())
                .board(board)
                .nodeId(dto.getNodeId())
                .nodeType(dto.getType())
                .verticalPosition(dto.getVerticalPosition())
                .horizontalPosition(dto.getHorizontalPosition())
                .maxSpeedAllowed(dto.getMaxSpeedAllowed())
                .bottomNodeId(dto.getBottomId())
                .rightNodeId(dto.getRightId())
                .topNodeId(dto.getTopId())
                .leftNodeId(dto.getLeftId())
                .spawnStreamId(dto.getSpawnStreamId())
                .build();
    }

    private static Node toRoadNode(final NodeEntity entity) {
        return RoadNode.builder()
                .id(NodeId.of(entity.getNodeId()))
                .type(entity.getNodeType())
                .maxSpeedAllowed(entity.getMaxSpeedAllowed())
                .position(NodePosition.of(entity.getHorizontalPosition(), entity.getVerticalPosition()))
                .build();
    }

    private static Node toSpawnNode(NodeEntity entity) {
        return SpawnCarNode.builder()
                .id(NodeId.of(entity.getNodeId()))
                .spawnStreamId(SpawnStreamId.of(entity.getSpawnStreamId()))
                .type(entity.getNodeType())
                .maxSpeedAllowed(entity.getMaxSpeedAllowed())
                .position(NodePosition.of(entity.getHorizontalPosition(), entity.getVerticalPosition()))
                .build();
    }


    private static void setNeighbours(final NodeEntity entity, final Map<String, Node> map) {
        map.get(entity.getNodeId())
                .setNeighbors(Neighbors.of(map.get(entity.getTopNodeId()),
                        map.get(entity.getLeftNodeId()),
                        map.get(entity.getRightNodeId()),
                        map.get(entity.getBottomNodeId())));
    }
}
