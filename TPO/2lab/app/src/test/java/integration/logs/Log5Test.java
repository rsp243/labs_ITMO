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
import log.Log5;

public class Log5Test {
    private Ln lnMock;
    private Log5 log5;
    private static final double PRECISION = 1e-8;

    @BeforeEach
    void initMock() {
        lnMock = mock(Ln.class);

        when(lnMock.calculate(1.0)).thenReturn(0.0);

        when(lnMock.calculate(5.0)).thenReturn(Math.log(5));

        when(lnMock.calculate(0.5)).thenReturn(Math.log(0.5));

        when(lnMock.calculate(25.0)).thenReturn(Math.log(25));
        when(lnMock.calculate(125.0)).thenReturn(Math.log(125));

        when(lnMock.calculate(6.0)).thenReturn(Math.log(6));

        log5 = new Log5(lnMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            6.0,
            125.0,
            25.0,
            0.5,
            5.0,
            1.0,
    })
    public void testLog5MockValues(double x) {
        double expected = Math.log(x) / Math.log(5);
        double actual = log5.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLog5UndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> log5.calculate(0.0));
    }

    @Test
    public void testLog5UndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> log5.calculate(-1.0));
    }
}
