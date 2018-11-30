package com.miss.artificial_city.infastructure.db.repositories;

import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoardDao extends JpaRepository<BoardEntity, String> {
    BoardEntity findByName(String name);

    @Query(value = "SELECT b.BOARD_NAME from BOARD_TABLE b",nativeQuery = true)
    List<String> getBoardNames();
}
