package bbk.sp2.cw3.model;

import java.util.List;

public class Building {

    private int numOfFloors;
    private List<Customer> customerList;
    private Elevator elevator;

    public Building(int numOfFloors, List<Customer> customerList, Elevator elevator) {
        this.numOfFloors = numOfFloors;
        this.customerList = customerList;
        this.elevator = elevator;
    }

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Elevator getElevator() {
        return elevator;
    }
}