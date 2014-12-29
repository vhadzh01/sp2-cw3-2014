package bbk.sp2.cw3.controller.input;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import bbk.sp2.cw3.service.ValidationService;

@RunWith(MockitoJUnitRunner.class)
public class FloorInputTest {

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private FloorInput underTest;

    @Test
    public void testIsValidInput() {

        when(validationService.isNumberOfFloorsValid(anyInt()))
                .thenReturn(true);

        boolean result = underTest.isValidInput(1);

        assertTrue(result);
    }
}