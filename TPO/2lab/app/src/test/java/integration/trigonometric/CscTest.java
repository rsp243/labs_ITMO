package integration.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import trigonometric.Csc;
import trigonometric.Sin;

public class CscTest {
    private Sin sinMock;
    private Csc csc;
    private static final double PRECISION = 1e-10;

    @BeforeEach
    void initMock() {
        sinMock = mock(Sin.class);

        when(sinMock.calculate(0.0)).thenReturn(0.0); // sin(0) = 0
        when(sinMock.calculate(Math.PI)).thenReturn(0.0); // sin(π) = 0
        when(sinMock.calculate(2 * Math.PI)).thenReturn(0.0); // sin(2π) = 0

        when(sinMock.calculate(Math.PI / 2)).thenReturn(1.0); // sin(π/2) = 1
        when(sinMock.calculate(5 * Math.PI / 2)).thenReturn(1.0); // sin(5π/2) = 1

        when(sinMock.calculate(3 * Math.PI / 2)).thenReturn(-1.0); // sin(3π/2) = -1
        when(sinMock.calculate(-Math.PI / 2)).thenReturn(-1.0); // sin(-π/2) = -1

        when(sinMock.calculate(Math.PI / 6)).thenReturn(0.5); // sin(π/6) = 0.5
        when(sinMock.calculate(5 * Math.PI / 6)).thenReturn(0.5); // sin(5π/6) = 0.5

        csc = new Csc(sinMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Math.PI / 2,
            5 * Math.PI / 2,
            3 * Math.PI / 2,
            -Math.PI / 2,
            5 * Math.PI / 6,
            5 * Math.PI / 6,
    })
    public void testCscMockValues(double x) {
        double expected = 1 / Math.sin(x);
        double actual = csc.calculate(x);
        assertEquals(expected, actual, PRECISION);
        verify(sinMock, times(1)).calculate(x);
    }

    @Test
    public void testCscUndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> csc.calculate(0.0));
    }
}
