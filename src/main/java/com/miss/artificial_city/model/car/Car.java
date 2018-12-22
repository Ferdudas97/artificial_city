package com.miss.artificial_city.model.car;


import com.miss.artificial_city.model.node.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor(staticName = "of")
@Getter
public class Car {
    private Double currentSpeed;
    private Double maxSpeed;
    private Node head;
    private int size;
    private CarId id;
    private final Function<Double, Double> accelerationFunction;


    public Car(final Node head, final int size, final Double maxSpeed) {
        this.id = CarId.of(UUID.randomUUID().toString());
        this.accelerationFunction = speed -> Math.cos(speed + 1);
        this.head=head;
        this.size =size;
        this.maxSpeed = maxSpeed;

    }

    public void move() {
        val previousHead = head;
        int distance = currentSpeed.intValue();
        boolean shouldChange = isStripEndingSoon(head, distance);
        if (shouldChange) changeStrip();
        else {
            changeToTheFastestPossible();
        }
        currentSpeed = getMaximumPossibleSpeedOnStrip(head);
        removePresenceFromPreviousNodes(previousHead);
        moveNodes(head);

    }

    private void moveNodes(Node node) {
        for (int i = 0; i < currentSpeed.intValue(); i++) {
            node = node.getNeighbors().getRight();
        }
        setPresenceToNewNodes(node);

    }

    private void changeStrip() {
        Node newHead = getTheNodeWithTheLongestDistance();
        boolean isChanging = randomDecisionAboutChangingStrip(newHead);
        if (isChanging) head = newHead;
    }

    private void changeToTheFastestPossible() {
        Node nodeWithTheHigherPossibleSpeed = getStripWithMaximumPossibleSpeed();
        if (head != nodeWithTheHigherPossibleSpeed && randomDecisionAboutChangingStrip(nodeWithTheHigherPossibleSpeed))
            head = nodeWithTheHigherPossibleSpeed;

    }

    private void setPresenceToNewNodes(Node node) {
        for (int i = 0; i < size; i++) {
            node.setCarId(id);
            node.setIsTaken(true);
            node = node.getNeighbors().getRight();
        }
    }

    private void removePresenceFromPreviousNodes(Node node) {
        for (int i = 0; i < size; i++) {
            node.setIsTaken(false);
            node.setCarId(null);
            node = node.getNeighbors().getLeft();
        }
    }

    private boolean isStripEndingSoon(Node node, double distance) {
        while (distance > 0 && node != null) {
            node = node.getNeighbors().getRight();
            distance--;
        }
        if (node == null || distance == 0) return true;
        return false;
    }

    private Node getTheNodeWithTheLongestDistance() {
        return Stream.of(head.getNeighbors().getTop(), head.getNeighbors().getBottom(), head)
                .collect(Collectors.toMap(Function.identity(), this::checkStripDistance))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }


    private boolean randomDecisionAboutChangingStrip(Node node) {
        val random = new Random();
        val difference = getDifferenceBeetwenDistanceAndSpeedOfTheClosestCar(node);
        int probabilty;
        if (difference > 0) probabilty = 9;
        else probabilty = 2;
        return random.nextInt(10) <= probabilty;
    }

    private Double getDifferenceBeetwenDistanceAndSpeedOfTheClosestCar(Node node) {
        int distance = 0;
        while (node != null && !node.getIsTaken()) {
            node = node.getNeighbors().getLeft();
            distance++;
        }
        double carSpeed = Optional.ofNullable(node)
                .map(Node::getCarId)
                .map(CarHolder.cars::get)
                .map(Car::getCurrentSpeed)
                .orElse(0.0);

        return distance - carSpeed;

    }

    private Node getStripWithMaximumPossibleSpeed() {
        return Stream.of(head.getNeighbors().getTop(), head.getNeighbors().getBottom(), head)
                .collect(Collectors.toMap(Function.identity(), this::getMaximumPossibleSpeedOnStrip))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }

    private Double getMaximumPossibleSpeedOnStrip(Node node) {
        int dist = 0;
        while (node != null && !node.getIsTaken() && dist < maxSpeed) {
            dist++;
            node = node.getNeighbors().getRight();
        }
        if (shouldBrake(dist)) return brakeSpeed(dist);
        else return acceleratedSpeed();
    }

    private boolean shouldBrake(final int distance) {
        return distance - acceleratedSpeed() < 0;
    }

    private int checkStripDistance(Node node) {
        int dist = 0;
        //lub         while (node != null && !node.getIsTaken()) {
        while (node != null) {
            dist++;
            node = node.getNeighbors().getRight();
        }
        return dist;
    }

    private Double acceleratedSpeed() {
        return Math.min(maxSpeed, currentSpeed + accelerationFunction.apply(currentSpeed));
    }

    private Double brakeSpeed(int dist) {
        return Math.min(currentSpeed, dist - 1);
    }

}
