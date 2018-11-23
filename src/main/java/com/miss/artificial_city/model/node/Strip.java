package com.miss.artificial_city.model.node;

import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Strip {
    private final List<Node> nodes;
    private final int length;

    public Strip(final int length) {
        nodes = new ArrayList<>();
        this.length = length;
        for (int i = 0; i < length; i++) {
            val node = new Node();
            nodes.add(node);
            if (i > 0) {
                val nodeBefore = nodes.get(i - 1);
                nodeBefore.getNeighbors().setRight(node);
                node.getNeighbors().setLeft(nodeBefore);
            }

        }
    }
}
