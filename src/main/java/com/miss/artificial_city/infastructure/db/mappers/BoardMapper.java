package com.miss.artificial_city.infastructure.db.mappers;

import com.miss.artificial_city.dto.BoardDto;
import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import lombok.val;

import java.util.UUID;

public class BoardMapper {
    public static BoardEntity toEntity(final BoardDto dto) {
        val entity = BoardEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(dto.getBoardName())
                .build();
        entity.setNodeEntities(NodeMapper.toEntity(dto.getNodeDtos(),entity));
        return entity;
    }

    public static BoardDto toDto(final BoardEntity entity) {
        return BoardDto.of(entity.getName(),NodeMapper.toDto(entity.getNodeEntities()));
    }
}
