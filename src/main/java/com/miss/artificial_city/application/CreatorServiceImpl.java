package com.miss.artificial_city.application;

import com.miss.artificial_city.application.exceptions.BoardNotFoundException;
import com.miss.artificial_city.dto.GetSavedBoardResponse;
import com.miss.artificial_city.dto.SaveBoardRequest;
import com.miss.artificial_city.infastructure.db.mappers.BoardMapper;
import com.miss.artificial_city.infastructure.db.repositories.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Stream.of(request)
                .map(SaveBoardRequest::getBoardDto).
                map(BoardMapper::toEntity)
                .forEach(boardDao::save);

    }

    @Override
    public List<String> getAllBoardNames() {
        return boardDao.getBoardNames();
    }

}
