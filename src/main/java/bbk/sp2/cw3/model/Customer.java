package bbk.sp2.cw3.model;

public class Customer {

    private int id;
    private int currentFloor;
    private int destinationFloor;
    private boolean inElevator;
    private boolean finished;

    public Customer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public boolean isInElevator() {
        return inElevator;
    }

    public void setInElevator(boolean inElevator) {
        this.inElevator = inElevator;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Customer[" +id +']';
    }
}