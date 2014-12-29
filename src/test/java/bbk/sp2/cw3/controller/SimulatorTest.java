package bbk.sp2.cw3.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import bbk.sp2.cw3.model.Building;
import bbk.sp2.cw3.model.Customer;
import bbk.sp2.cw3.model.Elevator;

@RunWith(MockitoJUnitRunner.class)
public class SimulatorTest {

    @Mock
    private Building building;

    @Mock
    private Elevator elevator;

    @Mock
    private Customer customer;

    @Mock
    private Customer otherCustomer;

    private Simulator underTest;

    @Before
    public void setUp() {

        when(building.getElevator()).thenReturn(elevator);

        underTest = new Simulator(building);
    }

    @Test
    public void testElevatorMovingDownForCustomersToLeaveWhenOnGroundFloor() {

        when(elevator.getCurrentFloor()).thenReturn(0);

        underTest.elevatorMovingDownForCustomersToLeave();

        verify(elevator, times(0)).customerLeaves(any(Customer.class));
    }

    @Test
    public void testElevatorMovingDownForCustomersToLeaveWhenNotOnGroundFloor() {

        List<Customer> customerList = Arrays
                .asList(new Customer[] { customer });

        when(elevator.getCurrentFloor()).thenReturn(1).thenReturn(1)
                .thenReturn(0);
        when(elevator.getRegisterList()).thenReturn(customerList);
        when(customer.getDestinationFloor()).thenReturn(1);

        underTest.elevatorMovingDownForCustomersToLeave();

        verify(elevator, times(1)).customerLeaves(customer);
    }

    @Test
    public void testElevatorMovingUpForCustomersToJoinWhenOnTopFloor() {

        when(elevator.getCurrentFloor()).thenReturn(1);

        underTest.elevatorMovingUpForCustomersToJoin(1);

        verify(elevator, times(0)).customerJoins(any(Customer.class));
    }

    @Test
    public void testElevatorMovingUpForCustomersToJoinWhenOnGroundFloor() {

        List<Customer> customerList = Arrays
                .asList(new Customer[] { customer });

        when(elevator.getCurrentFloor()).thenReturn(0).thenReturn(0)
                .thenReturn(1);
        when(building.getCustomerList()).thenReturn(customerList);
        when(customer.getCurrentFloor()).thenReturn(0);

        underTest.elevatorMovingUpForCustomersToJoin(1);

        verify(elevator, times(1)).customerJoins(customer);
    }

    @Test
    public void testGetHighestDestinationFloor() {

        List<Customer> customerList = Arrays.asList(new Customer[] { customer,
                otherCustomer });

        when(elevator.getRegisterList()).thenReturn(customerList);
        when(customer.getDestinationFloor()).thenReturn(1);
        when(otherCustomer.getDestinationFloor()).thenReturn(2);

        int result = underTest.getHighestDestinationFloor();

        assertEquals(2, result);
    }

    @Test
    public void testGetHighestFloorWithCustomer() {

        List<Customer> customerList = Arrays.asList(new Customer[] { customer,
                otherCustomer });

        when(building.getCustomerList()).thenReturn(customerList);
        when(customer.getCurrentFloor()).thenReturn(1);
        when(otherCustomer.getCurrentFloor()).thenReturn(2);

        int result = underTest.getHighestFloorWithCustomer();

        assertEquals(2, result);
    }
}