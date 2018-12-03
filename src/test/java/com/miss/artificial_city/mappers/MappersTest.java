package com.miss.artificial_city.mappers;


import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import com.miss.artificial_city.infastructure.db.entities.NodeEntity;
import com.miss.artificial_city.infastructure.db.mappers.BoardMapper;
import com.miss.artificial_city.infastructure.db.mappers.NodeMapper;
import com.miss.artificial_city.infastructure.db.repositories.BoardDao;
import com.miss.artificial_city.model.node.Node;
import com.miss.artificial_city.model.node.NodeType;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MappersTest {

    private BoardEntity boardEntity;
    @Before
    public void setUp() {
       boardEntity = NodeMocks.mockBoardEntity();
    }
    @Test
   public void toDtoTestMapper() {
        val boardDto = BoardMapper.toDto(boardEntity);
        val entity = BoardMapper.toEntity(boardDto);
        Assert.assertEquals(boardEntity, entity);
    }

    @Test
    public void toDomainTest() {
        val domainNodes = NodeMapper.toDomain(boardEntity.getNodeEntities());
        Assert.assertEquals(domainNodes.size(), boardEntity.getNodeEntities().size());
    }

}
