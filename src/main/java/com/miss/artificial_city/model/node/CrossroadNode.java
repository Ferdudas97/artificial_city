package com.miss.artificial_city.model.node;

import com.miss.artificial_city.model.car.CarId;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CrossroadNode extends Node {

    Node left;
    Node right;
    Node top;
    Node bottom;

    public CrossroadNode(@NotNull NodeType type, @NotNull NodeId id, @NotNull Boolean isTaken, Neighbors neighbors, Double maxSpeedAllowed, @NotNull NodePosition position, CarId carId) {
        super(type, id, isTaken, neighbors, maxSpeedAllowed, position, carId);
    }

    private void connectRight() {
        this.getNeighbors().setRight(this.right);
        this.right.getNeighbors().setLeft(this.left);
    }

    private void disConnectRight() {
        this.getNeighbors().setRight(this.right);
        this.right.getNeighbors().setLeft(this.left);
    }
    private void connectTop() {
        this.getNeighbors().setTop(this.top);
        this.top.getNeighbors().setLeft(this.left);
    }

    private void disConnectTop() {
        this.getNeighbors().setTop(null);
        this.top.getNeighbors().setLeft(null);
    }
    private void connectBottom() {
        this.getNeighbors().setBottom(this.bottom);
        this.bottom.getNeighbors().setLeft(this.left);
    }

    private void disConnectBottom() {
        this.getNeighbors().setBottom(null);
        this.right.getNeighbors().setLeft(null);
    }
}
