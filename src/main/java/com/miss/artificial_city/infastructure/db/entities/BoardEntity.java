package com.miss.artificial_city.infastructure.db.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Setter
@Getter
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
