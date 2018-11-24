package com.miss.artificial_city.model;


import com.miss.artificial_city.model.node.Node;
import com.miss.artificial_city.model.node.NodeType;
import com.sun.glass.ui.Size;
import lombok.AllArgsConstructor;
import lombok.val;
import lombok.var;
import sun.security.util.Length;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(staticName = "of")
public class Car {
    private Double acceleration;
    private Double currentSpeed;
    private Double maxSpeed;
    private Node head;
    private int size;

    public void move() {

    }

    private boolean shouldChangeStrip() {
        int distance = currentSpeed.intValue();
        boolean shouldChange = shouldChangeStrip(head, distance);
        if (shouldChange) {
          getTheLongestDistance();
        }
        return true;
    }


    private boolean shouldChangeStrip(final Node node, final double distance) {
        if (distance > 0 && node == null) return false;
        if (distance > 0) shouldChangeStrip(node.getNeighbors().getRight(), distance - 1);
        return true;
    }

    private Node getTheLongestDistance(){
        return Stream.of(head.getNeighbors().getTop(), head.getNeighbors().getBottom(), head)
                .collect(Collectors.toMap(Function.identity(), this::checkDistance))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }

    private int checkDistance(final Node startNode) {
        int dist = 0;
        var node = startNode;
        while (node != null && !node.getIsTaken()) {
            dist++;
            node = node.getNeighbors().getRight();
        }
        return dist;
    }
    private void updateCurrentSpeed() {
        currentSpeed = Math.min(maxSpeed, currentSpeed + acceleration);
    }

    private void brake(int dist) {
        currentSpeed = Math.min(currentSpeed, dist);
    }

}
