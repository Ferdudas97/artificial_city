package com.miss.artificial_city.model.car;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
@Getter
@ToString
public class CarId {
    private final String id;
}
