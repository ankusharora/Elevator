package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;

class Elevator {
    private final int id;
    private int currentFloor;
    private State state;
    private Direction direction;
    private final PriorityQueue<Integer> upQueue;
    private final PriorityQueue<Integer> downQueue;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1; // Assuming ground floor is 1
        this.state = State.IDLE;
        this.direction = Direction.UP;
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor; // Added the missing getter for currentFloor
    }

    public void addRequest(Request request) {
        ExternalRequest externalRequest = request.getExternalRequest();
        InternalRequest internalRequest = request.getInternalRequest();

        if (externalRequest != null) {
            int sourceFloor = externalRequest.getSourceFloor();
            Direction requestDirection = externalRequest.getDirection();

            if (currentFloor == sourceFloor) {
                // If already at the source floor, add the request directly
                if (requestDirection == Direction.UP) {
                    upQueue.add(sourceFloor);
                } else {
                    downQueue.add(sourceFloor);
                }
            } else {
                // First move to the source floor
                if (sourceFloor > currentFloor) {
                    upQueue.add(sourceFloor);
                } else {
                    downQueue.add(sourceFloor);
                }
            }
        }

        if (internalRequest != null) {
            int destinationFloor = internalRequest.getDestinationFloor();
            if (destinationFloor > currentFloor) {
                upQueue.add(destinationFloor);
            } else {
                downQueue.add(destinationFloor);
            }
        }

        processRequests();
    }


    public void addRequest1(Request request) {
        ExternalRequest externalRequest = request.getExternalRequest();
        InternalRequest internalRequest = request.getInternalRequest();

        if (externalRequest != null) {
            if (externalRequest.getDirection() == Direction.UP) {
                upQueue.add(externalRequest.getSourceFloor());
            } else {
                downQueue.add(externalRequest.getSourceFloor());
            }
        }

        if (internalRequest != null) {
            int destinationFloor = internalRequest.getDestinationFloor();
            if (destinationFloor > currentFloor) {
                upQueue.add(destinationFloor);
            } else {
                downQueue.add(destinationFloor);
            }
        }

        processRequests();
    }

    private void processRequests() {
        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            if (state == State.IDLE || state == State.STOPPED) {
                if (!upQueue.isEmpty()) {
                    direction = Direction.UP;
                    state = State.MOVING;
                } else if (!downQueue.isEmpty()) {
                    direction = Direction.DOWN;
                    state = State.MOVING;
                }
            }

            if (direction == Direction.UP && !upQueue.isEmpty()) {
                currentFloor = upQueue.poll();
                System.out.println("Elevator " + id + " moving up to floor " + currentFloor);
            } else if (direction == Direction.DOWN && !downQueue.isEmpty()) {
                currentFloor = downQueue.poll();
                System.out.println("Elevator " + id + " moving down to floor " + currentFloor);
            }

            state = State.STOPPED;
            System.out.println("Elevator " + id + " stopped at floor " + currentFloor);
        }

        state = State.IDLE;
    }
}
