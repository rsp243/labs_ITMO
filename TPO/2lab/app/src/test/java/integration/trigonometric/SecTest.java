package integration.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import trigonometric.Cos;
import trigonometric.Sec;

public class SecTest {
    private Cos cosMock;
    private Sec sec;
    private static final double PRECISION = 1e-10;

    @BeforeEach
    void initMock() {
        cosMock = mock(Cos.class);

        when(cosMock.calculate(Math.PI / 2)).thenReturn(0.0);
        when(cosMock.calculate(3 * Math.PI / 2)).thenReturn(0.0);

        when(cosMock.calculate(0.0)).thenReturn(1.0);
        when(cosMock.calculate(2 * Math.PI)).thenReturn(1.0);

        when(cosMock.calculate(Math.PI)).thenReturn(-1.0);
        when(cosMock.calculate(-Math.PI)).thenReturn(-1.0);

        when(cosMock.calculate(Math.PI / 3)).thenReturn(0.5);
        when(cosMock.calculate(5 * Math.PI / 3)).thenReturn(0.5);

        sec = new Sec(cosMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            2 * Math.PI,
            Math.PI,
            -Math.PI,
            Math.PI / 3,
            5 * Math.PI / 3,
    })
    public void testSecMockValues(double x) {
        double expected = 1 / Math.cos(x);
        double actual = sec.calculate(x);
        assertEquals(expected, actual, PRECISION);
        verify(cosMock, times(1)).calculate(x);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Math.PI / 2,
            3 * Math.PI / 2
    })
    public void testSecUndefinedAtPiDividedBy2(double x) {
        assertThrows(ArithmeticException.class, () -> sec.calculate(x));
    }
}
