package com.miss.artificial_city.infastructure.db.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "BOARD_TABLE")
public class BoardEntity {

    @Id
    @GeneratedValue
    private String id;
    @Column(name = "BOARD_NAME",nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "board")
    private Set<NodeEntity> nodeEntities;
}
