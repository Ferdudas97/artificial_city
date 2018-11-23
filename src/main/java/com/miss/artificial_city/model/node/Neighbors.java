package com.miss.artificial_city.model.node;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(staticName = "of")
@Setter
public class Neighbors {
    private  Node top;
    private  Node left;
    private  Node right;
    private  Node bottom;
}
