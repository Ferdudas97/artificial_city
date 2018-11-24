package com.miss.artificial_city.model.node;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class CrossroadNode extends Node {

    Node left;
    Node right;
    Node top;
    Node bottom;

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
