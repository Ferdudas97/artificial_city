package com.miss.artificial_city.model.node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(staticName = "of")
@Setter
@Getter
public class Neighbors {
    private  Node top;
    private  Node left;
    private  Node right;
    private  Node bottom;
}
