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
        Elevator closestElevator = findClosestElevator(request.getExternalRequest().getSourceFloor());
        closestElevator.addRequest(request);
    }

    private Elevator findClosestElevator(int sourceFloor) {
        return elevators.stream()
                .min(Comparator.comparingInt(e -> Math.abs(e.getCurrentFloor() - sourceFloor)))
                .orElseThrow(() -> new RuntimeException("No elevators available"));
    }
}

