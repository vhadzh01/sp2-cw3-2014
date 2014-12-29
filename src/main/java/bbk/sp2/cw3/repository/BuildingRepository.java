package bbk.sp2.cw3.repository;

import bbk.sp2.cw3.model.Building;
import bbk.sp2.cw3.model.Customer;
import bbk.sp2.cw3.model.Elevator;
import bbk.sp2.cw3.service.ValidationService;

import java.util.List;

public class BuildingRepository extends Repository {

    public BuildingRepository(ValidationService validationService) {
        super(validationService);
    }

    public Building create(int numOfFloors, List<Customer> customerList, Elevator elevator) {

        if (!getValidationService().isNumberOfFloorsValid(numOfFloors)) {
            throw new IllegalArgumentException("Specify at least 2 floors");
        }

        if (customerList == null || !getValidationService().isNumberOfCustomersValid(customerList.size())) {
            throw new IllegalArgumentException("Specify at least 2 customers");
        }

        return new Building(numOfFloors, customerList, elevator);
    }
}