package com.miss.artificial_city.infastructure.repository;

import com.miss.artificial_city.infastructure.db.entities.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends JpaRepository<BoardEntity, String> {
}
