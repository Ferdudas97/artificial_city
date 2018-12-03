package com.miss.artificial_city.infastructure.db.repositories;

import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardDao extends JpaRepository<BoardEntity, String> {

    @Query(value = "SELECT b.id, b.BOARD_NAME from BOARD_TABLE b WHERE b.BOARD_NAME = :name",nativeQuery = true)
    BoardEntity findByName(@Param("name") String name);

    @Query(value = "SELECT b.BOARD_NAME from BOARD_TABLE b",nativeQuery = true)
    List<String> getBoardNames();
}
