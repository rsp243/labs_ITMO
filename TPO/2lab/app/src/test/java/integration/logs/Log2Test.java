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
import log.Log2;

public class Log2Test {
    private Ln lnMock;
    private Log2 log2;
    private static final double PRECISION = 1e-8;

    @BeforeEach
    void initMock() {
        lnMock = mock(Ln.class);

        when(lnMock.calculate(1.0)).thenReturn(0.0);

        when(lnMock.calculate(2.0)).thenReturn(Math.log(2));

        when(lnMock.calculate(0.5)).thenReturn(Math.log(0.5));

        when(lnMock.calculate(4.0)).thenReturn(Math.log(4));
        when(lnMock.calculate(8.0)).thenReturn(Math.log(8));

        when(lnMock.calculate(3.0)).thenReturn(Math.log(3));

        log2 = new Log2(lnMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.0,
            2.0,
            0.5,
            4.0,
            8.0,
            3.0,
    })
    public void testLog2MockValues(double x) {
        double expected = Math.log(x) / Math.log(2);
        double actual = log2.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLog2UndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> log2.calculate(0.0));
    }

    @Test
    public void testLog2UndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> log2.calculate(-1.0));
    }
}
