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

import trigonometric.Cos;
import trigonometric.Cot;
import trigonometric.Sin;

public class CotTest {
    private Sin sinMock;
    private Cos cosMock;
    private Cot cot;
    private static final double PRECISION = 1e-10;

    @BeforeEach
    void initMock() {
        sinMock = mock(Sin.class);
        cosMock = mock(Cos.class);

        when(sinMock.calculate(0.0)).thenReturn(0.0);
        when(sinMock.calculate(Math.PI)).thenReturn(0.0);
        when(sinMock.calculate(2 * Math.PI)).thenReturn(0.0);

        when(sinMock.calculate(Math.PI / 2)).thenReturn(1.0);
        when(cosMock.calculate(Math.PI / 2)).thenReturn(0.0);

        when(sinMock.calculate(3 * Math.PI / 2)).thenReturn(-1.0);
        when(cosMock.calculate(3 * Math.PI / 2)).thenReturn(0.0);

        when(sinMock.calculate(Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
        when(cosMock.calculate(Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);

        when(sinMock.calculate(3 * Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
        when(cosMock.calculate(3 * Math.PI / 4)).thenReturn(-Math.sqrt(2) / 2);

        cot = new Cot(sinMock, cosMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Math.PI / 4,
            3 * Math.PI / 4,
            Math.PI / 2,
            3 * Math.PI / 2,
    })
    public void testCotMockValues(double x) {
        double expected = 1 / Math.tan(x);
        double actual = cot.calculate(x);
        assertEquals(expected, actual, PRECISION);
        verify(sinMock, times(1)).calculate(x);
        verify(cosMock, times(1)).calculate(x);
    }

    @Test
    public void testCotUndefinedAtPi() {
        assertThrows(ArithmeticException.class, () -> cot.calculate(Math.PI));
    }
}
