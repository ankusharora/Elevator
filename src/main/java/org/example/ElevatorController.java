package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ElevatorController {
    private final List<Elevator> elevators;

    public ElevatorController(int numberOfElevators) {
        this.elevators = new ArrayList<>();
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(i + 1));
        }
    }

    public void handleRequest(Request request) {
        Elevator closestElevator = findClosestElevator(request.getExternalRequest().getSourceFloor(), request.getExternalRequest().getDirection());
        closestElevator.addRequest(request);
    }

    private Elevator findClosestElevator(int sourceFloor, Direction requestDirection) {
        return elevators.stream()
                .filter(elevator -> {
                    if (elevator.getState() == State.IDLE) {
                        return true; // Idle elevators are always eligible
                    }
                    if (elevator.getState() == State.MOVING) {
                        // Check if the elevator's direction matches the request
                        if (elevator.getDirection() == requestDirection) {
                            if (requestDirection == Direction.UP) {
                                return elevator.getCurrentFloor() <= sourceFloor; // It will pass the floor
                            } else if (requestDirection == Direction.DOWN) {
                                return elevator.getCurrentFloor() >= sourceFloor; // It will pass the floor
                            }
                        }
                    }
                    return false; // Other elevators are not eligible
                })
                .min(Comparator.comparingInt(elevator -> Math.abs(elevator.getCurrentFloor() - sourceFloor)))
                .orElseThrow(() -> new RuntimeException("No elevators available"));
    }

}

