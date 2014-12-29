package bbk.sp2.cw3.model;

import java.util.ArrayList;
import java.util.List;

public class Elevator {

    private int numOfFloors;
    private List<Customer> registerList = new ArrayList<>();
    private int currentFloor;
    private Direction direction = Direction.UP;

    public Elevator(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public void move() {

        if (direction.equals(Direction.UP)) {

            if (currentFloor == numOfFloors) {
                throw new IllegalArgumentException("Already on top floor");
            }

            currentFloor++;
        } else {

            if (currentFloor == 0) {
                throw new IllegalArgumentException("Already on ground floor");
            }

            currentFloor--;
        }
    }

    public boolean customerJoins(Customer customer) {
        return registerList.add(customer);
    }

    public boolean customerLeaves(Customer customer) {
        return registerList.remove(customer);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public List<Customer> getRegisterList() {
        return registerList;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}