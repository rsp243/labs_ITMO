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
import log.Log10;

public class Log10Test {
    private Ln lnMock;
    private Log10 log10;
    private static final double PRECISION = 1e-8;

    @BeforeEach
    void initMock() {
        lnMock = mock(Ln.class);

        when(lnMock.calculate(1.0)).thenReturn(0.0);

        when(lnMock.calculate(10.0)).thenReturn(Math.log(10));

        when(lnMock.calculate(0.5)).thenReturn(Math.log(0.5));

        when(lnMock.calculate(100.0)).thenReturn(Math.log(100));
        when(lnMock.calculate(1000.0)).thenReturn(Math.log(1000));

        when(lnMock.calculate(11.0)).thenReturn(Math.log(11));

        log10 = new Log10(lnMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.0,
            10.0,
            0.5,
            100.0,
            1000.0,
            11.0,
    })
    public void testLog10MockValues(double x) {
        double expected = Math.log(x) / Math.log(10);
        double actual = log10.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLog10UndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> log10.calculate(0.0));
    }

    @Test
    public void testLog10UndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> log10.calculate(-1.0));
    }
}
