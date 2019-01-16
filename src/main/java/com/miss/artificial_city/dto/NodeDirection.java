package com.miss.artificial_city.dto;

public enum NodeDirection {
    LEFT, RIGHT,UP,DOWN;
    public static Boolean isOpposite(NodeDirection direction1, NodeDirection direction2) {
        return getOppositeDirection(direction1).equals(direction2);

    }

    private  static NodeDirection getOppositeDirection(NodeDirection direction){
        switch (direction) {
            case UP: return DOWN;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
            case DOWN: return UP;
        }
        return null;
    }
}
