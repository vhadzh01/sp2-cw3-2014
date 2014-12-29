package bbk.sp2.cw3.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationServiceTest {

    private ValidationService underTest = new ValidationService();

    @Test
    public void testIsNumberOfFloorsValidFalse() throws Exception {

        boolean result = underTest.isNumberOfFloorsValid(1);

        assertFalse(result);
    }

    @Test
    public void testIsNumberOfFloorsValidTrue() throws Exception {

        boolean result = underTest.isNumberOfFloorsValid(2);

        assertTrue(result);
    }

    @Test
    public void testIsNumberOfCustomersValidFalse() throws Exception {

        boolean result = underTest.isNumberOfCustomersValid(1);

        assertFalse(result);
    }

    @Test
    public void testIsNumberOfCustomersValidTrue() throws Exception {

        boolean result = underTest.isNumberOfCustomersValid(2);

        assertTrue(result);
    }

    @Test
    public void testIsCustomerIdValidFalse() throws Exception {

        boolean result = underTest.isCustomerIdValid(0);

        assertFalse(result);
    }

    @Test
    public void testIsCustomerIdValidTrue() throws Exception {

        boolean result = underTest.isCustomerIdValid(1);

        assertTrue(result);
    }
}