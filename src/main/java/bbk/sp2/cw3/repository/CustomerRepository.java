package bbk.sp2.cw3.repository;

import bbk.sp2.cw3.model.Customer;
import bbk.sp2.cw3.service.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerRepository extends Repository {

    public CustomerRepository(ValidationService validationService) {
        super(validationService);
    }

    public Customer create(int id) {

        if (!getValidationService().isCustomerIdValid(id)) {
            throw new IllegalArgumentException("Customer ID should be greater than 0");
        }

        return new Customer(id);
    }

    public List<Customer> createMultiple(int numberOfCustomers) {

        List<Customer> customerList = new ArrayList<>();

        for (int id = 1; id <= numberOfCustomers; id++) {
            customerList.add(create(id));
        }

        return customerList;
    }

    public void randomlyAssignCurrentAndDestinationFloors(List<Customer> customerList, int numberOfFloors) {

        if (customerList == null || customerList.size() < 1) {
            throw new IllegalArgumentException("Non-empty collection required");
        }

        for (Customer customer : customerList) {

            int currentFloor = getRandomNumberInRange(0, numberOfFloors);
            int destinationFloor;

            do {
                destinationFloor = getRandomNumberInRange(0, numberOfFloors);
            } while(destinationFloor == currentFloor);

            customer.setCurrentFloor(currentFloor);
            customer.setDestinationFloor(destinationFloor);
        }
    }

    private int getRandomNumberInRange(int min, int max) {

        return new Random().nextInt((max - min) + 1) + min;
    }
}