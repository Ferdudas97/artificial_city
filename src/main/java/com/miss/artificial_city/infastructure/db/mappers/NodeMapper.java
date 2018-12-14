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

    @FunctionalInterface
    private interface NeighboursSetter {
        void setNeighbours(final NodeEntity entity, final Map<String, Node> map);
    }

    private static Map<NodeDirection, NeighboursSetter> neighboursSetterMap;

    static {
        specifiedMappers = new HashMap<>();
        specifiedMappers.putIfAbsent(NodeType.ROAD, NodeMapper::toRoadNode);
        specifiedMappers.putIfAbsent(NodeType.SPAWN, NodeMapper::toSpawnNode);

        neighboursSetterMap = new HashMap<>();
        neighboursSetterMap.put(NodeDirection.LEFT, NodeMapper::setNeighboursWithLeftDirection);
        neighboursSetterMap.put(NodeDirection.RIGHT, NodeMapper::setNeighboursWithRightDirection);
        neighboursSetterMap.put(NodeDirection.UP, NodeMapper::setNeighboursWithTopDirection);
        neighboursSetterMap.put(NodeDirection.DOWN, NodeMapper::setNeighboursWithDownDirection);
    }


    public static Set<Node> toDomain(final Set<NodeEntity> entities) {
        val mapWithNodes =
                entities.stream().map(entity -> specifiedMappers.get(entity.getNodeType()).apply(entity))
                        .collect(Collectors.toMap(e -> e.getId().getId(), Function.identity()));

        entities.forEach(entity -> neighboursSetterMap.get(entity.getDirection()).setNeighbours(entity, mapWithNodes));
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
                .nodeType(entity.getNodeType())
                .nodeId(entity.getNodeId())
                .bottomId(entity.getBottomNodeId())
                .leftId(entity.getLeftNodeId())
                .topId(entity.getTopNodeId())
                .rightId(entity.getRightNodeId())
                .horizontalPosition(entity.getHorizontalPosition())
                .verticalPosition(entity.getVerticalPosition())
                .maxSpeedAllowed(entity.getMaxSpeedAllowed())
                .spawnStreamId(entity.getSpawnStreamId())
                .direction(entity.getDirection())
                .build();
    }

    private static NodeEntity toEntity(final NodeDto dto, final BoardEntity board) {
        return NodeEntity.builder()
                .id(UUID.randomUUID().toString())
                .board(board)
                .nodeId(dto.getNodeId())
                .nodeType(dto.getNodeType())
                .verticalPosition(dto.getVerticalPosition())
                .horizontalPosition(dto.getHorizontalPosition())
                .maxSpeedAllowed(dto.getMaxSpeedAllowed())
                .bottomNodeId(dto.getBottomId())
                .rightNodeId(dto.getRightId())
                .topNodeId(dto.getTopId())
                .leftNodeId(dto.getLeftId())
                .spawnStreamId(dto.getSpawnStreamId())
                .direction(dto.getDirection())
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


    private static void setNeighboursWithLeftDirection(final NodeEntity entity, final Map<String, Node> map) {
        map.get(entity.getNodeId())
                .setNeighbors(Neighbors.of(
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getBottomNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getRightNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getLeftNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getTopNodeId())));
    }

    private static void setNeighboursWithRightDirection(final NodeEntity entity, final Map<String, Node> map) {
        map.get(entity.getNodeId())
                .setNeighbors(Neighbors.of(
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getTopNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getLeftNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getRightNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getBottomNodeId())));
    }

    private static void setNeighboursWithTopDirection(final NodeEntity entity, final Map<String, Node> map) {
        map.get(entity.getNodeId())
                .setNeighbors(Neighbors.of(
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getLeftNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getBottomNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getTopNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getRightNodeId())));
    }


    private static void setNeighboursWithDownDirection(final NodeEntity entity, final Map<String, Node> map) {
        map.get(entity.getNodeId())
                .setNeighbors(Neighbors.of(
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getRightNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getTopNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getBottomNodeId()),
                        getNodeWithTheSameDirection(map, entity.getDirection(), entity.getLeftNodeId())));
    }

    private static Node getNodeWithTheSameDirection(final Map<String, Node> map,
                                                    final NodeDirection direction,
                                                    final String nodeId) {
        val node = map.get(nodeId);
        if (node == null) return null;
        return direction.equals(node.getDirection()) ? node : null;

    }
}
