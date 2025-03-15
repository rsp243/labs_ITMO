package integration.logs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import log.Ln;
import log.Log3;

public class Log3Test {
    private Ln lnMock;
    private Log3 log3;
    private static final double PRECISION = 1e-8;

    @BeforeEach
    void initMock() {
        lnMock = mock(Ln.class);

        when(lnMock.calculate(1.0)).thenReturn(0.0);

        when(lnMock.calculate(3.0)).thenReturn(Math.log(3));

        when(lnMock.calculate(0.5)).thenReturn(Math.log(0.5));

        when(lnMock.calculate(9.0)).thenReturn(Math.log(9));
        when(lnMock.calculate(27.0)).thenReturn(Math.log(27));

        when(lnMock.calculate(4.0)).thenReturn(Math.log(4));

        log3 = new Log3(lnMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.0,
            3.0,
            0.5,
            9.0,
            27.0,
            4.0,
    })
    public void testLog3MockValues(double x) {
        double expected = Math.log(x) / Math.log(3);
        double actual = log3.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLog3UndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> log3.calculate(0.0));
    }

    @Test
    public void testLog3UndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> log3.calculate(-1.0));
    }
}
