package bbk.sp2.cw3.service;

public class ValidationService {

    public boolean isNumberOfFloorsValid(int numOfFloors) {
        return numOfFloors > 1;
    }

    public boolean isNumberOfCustomersValid(int numOfCustomers) {
        return numOfCustomers > 1;
    }

    public boolean isCustomerIdValid(int id) {
        return id > 0;
    }
}