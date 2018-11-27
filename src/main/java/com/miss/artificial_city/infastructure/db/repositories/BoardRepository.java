package com.miss.artificial_city.infastructure.db.repositories;

import com.miss.artificial_city.dto.BoardDto;

import java.util.Set;

public interface BoardRepository {

    Set<BoardDto> getAllBoards();
    BoardDto getBoard(String name);
}
