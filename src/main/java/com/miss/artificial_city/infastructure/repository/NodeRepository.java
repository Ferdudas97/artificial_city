package com.miss.artificial_city.infastructure.repository;

import com.miss.artificial_city.infastructure.db.entities.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<NodeEntity, String> {
}
