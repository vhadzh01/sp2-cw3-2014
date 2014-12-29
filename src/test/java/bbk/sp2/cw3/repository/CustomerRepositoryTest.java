package bbk.sp2.cw3.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import bbk.sp2.cw3.model.Customer;
import bbk.sp2.cw3.service.ValidationService;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private CustomerRepository underTest;

    @Test
    public void testCreateWhenInvalidCustomerId() {

        int customerId = 0;

        when(validationService.isCustomerIdValid(customerId)).thenReturn(false);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Customer ID should be greater than 0");

        underTest.create(customerId);
    }

    @Test
    public void testCreateSuccess() {

        int customerId = 1;

        when(validationService.isCustomerIdValid(customerId)).thenReturn(true);

        Customer result = underTest.create(customerId);

        assertEquals(result.getId(), customerId);
    }

    @Test
    public void testCreateMultipleSuccess() {

        when(validationService.isCustomerIdValid(anyInt())).thenReturn(true);

        List<Customer> result = underTest.createMultiple(2);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
    }

    @Test
    public void testRandomlyAssignCurrentAndDestinationFloorsWhenCustomerListIsNull() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Non-empty collection required");

        underTest.randomlyAssignCurrentAndDestinationFloors(null, 0);
    }

    @Test
    public void testRandomlyAssignCurrentAndDestinationFloorsWhenCustomerListIsEmpty() {

        List<Customer> customerList = new ArrayList<>();

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Non-empty collection required");

        underTest.randomlyAssignCurrentAndDestinationFloors(customerList, 0);
    }

    @Test
    public void testRandomlyAssignCurrentAndDestinationFloorsSuccess() {

        List<Customer> customerList = Arrays.asList(new Customer[] {
                new Customer(1), new Customer(2) });

        int numberOfFloors = 2;

        underTest.randomlyAssignCurrentAndDestinationFloors(customerList,
                numberOfFloors);

        int customer1CurrentFloor = customerList.get(0).getCurrentFloor();
        int customer1DestinationFloor = customerList.get(0)
                .getDestinationFloor();

        int customer2CurrentFloor = customerList.get(1).getCurrentFloor();
        int customer2DestinationFloor = customerList.get(1)
                .getDestinationFloor();

        assertTrue(customer1CurrentFloor >= 0
                || customer1CurrentFloor <= numberOfFloors);
        assertTrue(customer1DestinationFloor >= 0
                || customer1DestinationFloor <= numberOfFloors);
        assertTrue(customer1CurrentFloor != customer1DestinationFloor);

        assertTrue(customer2CurrentFloor >= 0
                || customer2CurrentFloor <= numberOfFloors);
        assertTrue(customer2DestinationFloor >= 0
                || customer2DestinationFloor <= numberOfFloors);
        assertTrue(customer2CurrentFloor != customer2DestinationFloor);
    }
}