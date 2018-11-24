package com.miss.artificial_city.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class Pair<F,S> {
    private F first;
    private S second;

}
