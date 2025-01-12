package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int floors = 50;
        int elevators = 4;

        ElevatorController controller = new ElevatorController(elevators);

        // Example requests
        controller.handleRequest(new Request(new ExternalRequest(5, Direction.UP), new InternalRequest(10)));
        controller.handleRequest(new Request(new ExternalRequest(1, Direction.UP), new InternalRequest(15)));
        controller.handleRequest(new Request(new ExternalRequest(20, Direction.DOWN), new InternalRequest(5)));
    }
}