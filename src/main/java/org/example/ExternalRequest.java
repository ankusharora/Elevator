package org.example;

class ExternalRequest {
    private final int sourceFloor;
    private final Direction direction;

    public ExternalRequest(int sourceFloor, Direction direction) {
        this.sourceFloor = sourceFloor;
        this.direction = direction;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}