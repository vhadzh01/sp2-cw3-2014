package bbk.sp2.cw3.controller;

import bbk.sp2.cw3.model.Building;
import bbk.sp2.cw3.model.Customer;
import bbk.sp2.cw3.model.Direction;
import bbk.sp2.cw3.model.Elevator;

import java.util.ArrayList;
import java.util.List;

public class Simulator {

    private Building building;
    private Elevator elevator;

    private void printLogMessage(String message) {
        System.out.println("  [" + message + "]");
    }

    public Simulator(Building building) {
        this.building = building;
        this.elevator = building.getElevator();
    }

    public void runSimulation() {

        int highestFloorWithCustomer = getHighestFloorWithCustomer();

        elevatorMovingUpForCustomersToJoin(highestFloorWithCustomer);

        /*
         * See if there's a customer who wants to go to an even higher floor
         * than the current floor
         */
        int highestDestinationFloor = getHighestDestinationFloor();

        if (highestDestinationFloor > highestFloorWithCustomer) {

            for (int floor = highestFloorWithCustomer; floor < highestDestinationFloor; floor++) {
                printLogMessage("\u2191 Elevator is moving up...");
                elevator.setDirection(Direction.UP);
                elevator.move();
            }
        }

        elevatorMovingDownForCustomersToLeave();
    }

    void elevatorMovingDownForCustomersToLeave() {
        for (int floor = elevator.getCurrentFloor(); floor >= 0; floor--) {

            int currentFloor = elevator.getCurrentFloor();

            printLogMessage("Elevator is on floor " + currentFloor);

            List<Customer> customersLeavingOnCurrentFloor = getCustomersHavingDestinationFloorOnCurrentFloor(currentFloor);

            for (Customer customer : customersLeavingOnCurrentFloor) {

                printLogMessage("\u2190 Customer " + customer.getId()
                        + " leaves");
                elevator.customerLeaves(customer);
            }

            if (currentFloor > 0) {

                printLogMessage("\u2193 Elevator is moving down...");
                elevator.setDirection(Direction.DOWN);
                elevator.move();
            }
        }
    }

    void elevatorMovingUpForCustomersToJoin(int stopFloor) {

        for (int floor = elevator.getCurrentFloor(); floor <= stopFloor; floor++) {

            int currentFloor = elevator.getCurrentFloor();

            printLogMessage("Elevator is on floor " + currentFloor);

            List<Customer> customersOnCurrentFloor = getCustomersOnCurrentFloor(currentFloor);

            /*
             * Customers get in the elevator
             */
            for (Customer customer : customersOnCurrentFloor) {
                printLogMessage("\u2192 Customer " + customer.getId()
                        + " joins");
                elevator.customerJoins(customer);
            }

            if (currentFloor < stopFloor) {

                printLogMessage("\u2191 Elevator is moving up...");

                elevator.setDirection(Direction.UP);
                elevator.move();
            }
        }
    }

    int getHighestDestinationFloor() {

        int highestDestinationFloor = 0;

        for (Customer customer : elevator.getRegisterList()) {

            if (customer.getDestinationFloor() > highestDestinationFloor) {

                highestDestinationFloor = customer.getDestinationFloor();
            }
        }

        return highestDestinationFloor;
    }

    private List<Customer> getCustomersOnCurrentFloor(int currentFloor) {

        List<Customer> customersOnCurrentFloor = new ArrayList<>();

        for (Customer customer : building.getCustomerList()) {

            if (customer.getCurrentFloor() == currentFloor) {
                customersOnCurrentFloor.add(customer);
            }
        }

        return customersOnCurrentFloor;
    }

    private List<Customer> getCustomersHavingDestinationFloorOnCurrentFloor(
            int currentFloor) {

        List<Customer> customersHavingDestinationFloorOnCurrentFloor = new ArrayList<>();

        for (Customer customer : elevator.getRegisterList()) {

            if (customer.getDestinationFloor() == currentFloor) {

                customersHavingDestinationFloorOnCurrentFloor.add(customer);
            }
        }

        return customersHavingDestinationFloorOnCurrentFloor;
    }

    int getHighestFloorWithCustomer() {

        int highestFloorWithCustomer = 0;

        for (Customer customer : building.getCustomerList()) {

            int currentFloor = customer.getCurrentFloor();

            if (currentFloor > highestFloorWithCustomer) {
                highestFloorWithCustomer = currentFloor;
            }
        }

        return highestFloorWithCustomer;
    }
}