package com.miss.artificial_city.infastructure.db.mappers;

import
        com.miss.artificial_city.dto.NodeDto;
import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import com.miss.artificial_city.infastructure.db.entities.NodeEntity;

import java.util.*;
import java.util.stream.Collectors;

public class NodeMapper {


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
}
