package com.miss.artificial_city.application;

import com.miss.artificial_city.application.exceptions.BoardNotFoundException;
import com.miss.artificial_city.dto.GetSavedBoardResponse;
import com.miss.artificial_city.dto.SaveBoardRequest;
import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import com.miss.artificial_city.infastructure.db.entities.NodeEntity;
import com.miss.artificial_city.infastructure.db.mappers.BoardMapper;
import com.miss.artificial_city.infastructure.db.repositories.BoardDao;
import com.miss.artificial_city.model.node.NodeType;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CreatorServiceImpl implements CreatorService{

    private final BoardDao boardDao;

    @Autowired
    public CreatorServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }


    @Transactional
    @Override
    public GetSavedBoardResponse openSimulationBoard(String name) {

        return Stream.of(name).map(boardDao::findByName)
                .map(BoardMapper::toDto)
                .map(GetSavedBoardResponse::of).findFirst()
                .orElseThrow(BoardNotFoundException::new);
        //return GetSavedBoardResponse.of(BoardMapper.toDto(boardDao.findByName(name)));
    }

    @Transactional
    @Override
    public void saveSimulationBoard(SaveBoardRequest request) {


    }

    @Override
    public List<String> getAllBoardNames() {
        return boardDao.getBoardNames();
    }

    //TODO tylko na szybko do testów
    @Override
    public void save() {
        val boardEntity = BoardEntity.builder()
                .id("lol1")
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
                .build();
        Set<NodeEntity> nodeEntities = Stream.of(nodeEntity0,nodeEntity1,nodeEntity2,nodeEntity3)
                .collect(Collectors.toSet());
        boardEntity.setNodeEntities(nodeEntities);

        boardDao.save(boardEntity);
    }
}
