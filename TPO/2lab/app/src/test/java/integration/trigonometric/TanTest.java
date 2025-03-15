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
import trigonometric.Sin;
import trigonometric.Tan;

public class TanTest {
    private Sin sinMock;
    private Cos cosMock;
    private Tan tan;
    private static final double PRECISION = 1e-10;

    @BeforeEach
    void initMock() {
        // Создаем моки для Sin и Cos
        sinMock = mock(Sin.class);
        cosMock = mock(Cos.class);

        when(cosMock.calculate(Math.PI / 2)).thenReturn(0.0);
        when(cosMock.calculate(3 * Math.PI / 2)).thenReturn(0.0);

        when(sinMock.calculate(0.0)).thenReturn(0.0);
        when(cosMock.calculate(0.0)).thenReturn(1.0);
        when(sinMock.calculate(Math.PI)).thenReturn(0.0);
        when(cosMock.calculate(Math.PI)).thenReturn(-1.0);

        when(sinMock.calculate(Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
        when(cosMock.calculate(Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
        when(sinMock.calculate(3 * Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
        when(cosMock.calculate(3 * Math.PI / 4)).thenReturn(-Math.sqrt(2) / 2);

        when(sinMock.calculate(Math.PI / 6)).thenReturn(0.5);
        when(cosMock.calculate(Math.PI / 6)).thenReturn(Math.sqrt(3) / 2);
        when(sinMock.calculate(5 * Math.PI / 6)).thenReturn(0.5);
        when(cosMock.calculate(5 * Math.PI / 6)).thenReturn(-Math.sqrt(3) / 2);

        tan = new Tan(sinMock, cosMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            Math.PI,
            Math.PI / 4,
            3 * Math.PI / 4,
            Math.PI / 6,
            5 * Math.PI / 6,
    })
    public void testTanMockValues(double x) {
        double expected = Math.sin(x) / Math.cos(x);
        double actual = tan.calculate(x);
        assertEquals(expected, actual, PRECISION);
        verify(sinMock, times(1)).calculate(x);
        verify(cosMock, times(1)).calculate(x);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Math.PI / 2,
            3 * Math.PI / 2
    })
    public void testTanUndefinedAtPiDividedBy2(double x) {
        assertThrows(ArithmeticException.class, () -> tan.calculate(x));
    }
}
