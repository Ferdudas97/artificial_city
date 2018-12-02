package com.miss.artificial_city.infastructure.db.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "BOARD_TABLE")
public class BoardEntity {

    @Id
    private String id;

    @Column(name = "BOARD_NAME",nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "board")
    private Set<NodeEntity> nodeEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardEntity that = (BoardEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(nodeEntities, that.nodeEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
