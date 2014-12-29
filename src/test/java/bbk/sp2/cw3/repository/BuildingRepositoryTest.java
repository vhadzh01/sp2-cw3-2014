package bbk.sp2.cw3.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyInt;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import bbk.sp2.cw3.model.Building;
import bbk.sp2.cw3.model.Customer;
import bbk.sp2.cw3.model.Elevator;
import bbk.sp2.cw3.service.ValidationService;

@RunWith(MockitoJUnitRunner.class)
public class BuildingRepositoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ValidationService validationService;

    @Mock
    private List<Customer> customers;

    @Mock
    private Elevator elevator;

    @InjectMocks
    private BuildingRepository underTest;

    @Test
    public void testCreateWhenInvalidNumberOfFloors() throws Exception {

        int numOfFloors = 1;

        when(validationService.isNumberOfFloorsValid(numOfFloors)).thenReturn(
                false);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Specify at least 2 floors");

        underTest.create(numOfFloors, null, null);
    }

    @Test
    public void testCreateWhenCustomersIsNull() throws Exception {

        int numOfFloors = 2;

        when(validationService.isNumberOfFloorsValid(numOfFloors)).thenReturn(
                true);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Specify at least 2 customers");

        underTest.create(numOfFloors, null, null);
    }

    @Test
    public void testCreateWhenInvalidNumberOfCustomers() {

        int numOfFloors = 2;

        when(validationService.isNumberOfFloorsValid(numOfFloors)).thenReturn(
                true);
        when(validationService.isNumberOfCustomersValid(anyInt())).thenReturn(
                false);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Specify at least 2 customers");

        underTest.create(numOfFloors, customers, null);
    }

    @Test
    public void testCreateSuccess() throws Exception {

        int numOfFloors = 2;

        when(validationService.isNumberOfFloorsValid(numOfFloors)).thenReturn(
                true);
        when(validationService.isNumberOfCustomersValid(anyInt())).thenReturn(
                true);

        Building result = underTest.create(numOfFloors, customers, elevator);

        assertEquals(numOfFloors, result.getNumOfFloors());
        assertEquals(customers, result.getCustomerList());
        assertEquals(elevator, result.getElevator());
    }
}