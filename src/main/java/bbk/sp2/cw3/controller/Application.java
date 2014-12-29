package bbk.sp2.cw3.controller;

import bbk.sp2.cw3.controller.input.CustomerInput;
import bbk.sp2.cw3.controller.input.FloorInput;
import bbk.sp2.cw3.controller.input.UserInput;
import bbk.sp2.cw3.model.Building;
import bbk.sp2.cw3.model.Customer;
import bbk.sp2.cw3.model.Elevator;
import bbk.sp2.cw3.repository.BuildingRepository;
import bbk.sp2.cw3.repository.CustomerRepository;
import bbk.sp2.cw3.service.ValidationService;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ValidationService validationService = new ValidationService();
        BuildingRepository buildingRepository = new BuildingRepository(
                validationService);
        CustomerRepository customerRepository = new CustomerRepository(
                validationService);

        UserInput floorInput = new FloorInput(validationService, input);
        UserInput customerInput = new CustomerInput(validationService, input);

        System.out
                .println("\nWelcome to the elevator simulator!\n----------------------------------\n");

        int numberOfFloors = floorInput.getNumericValue(
                "Enter the number of floors",
                "Please specify at least 2 floors");

        int numberOfCustomers = customerInput.getNumericValue(
                "Enter the number of customers",
                "Please specify at least 2 customers");

        System.out.println("\nGenerating customers...");

        List<Customer> customerList = customerRepository
                .createMultiple(numberOfCustomers);

        System.out
                .println("Randomly assigning current and destination floors to customers...");

        customerRepository.randomlyAssignCurrentAndDestinationFloors(
                customerList, numberOfFloors);

        for (Customer customer : customerList) {
            System.out.println("\t[" + customer + " is on floor "
                    + customer.getCurrentFloor() + " and wants to go to floor "
                    + customer.getDestinationFloor() + "]");
        }

        System.out.println("Generating elevator...");

        Elevator elevator = new Elevator(numberOfFloors);

        System.out.println("Generating building with elevator...");

        Building building = buildingRepository.create(numberOfFloors,
                customerList, elevator);

        System.out.println("Simulation begins!\n");

        Simulator simulator = new Simulator(building);

        simulator.runSimulation();

        System.out.println("\nSimulation finished.\n");
    }
}