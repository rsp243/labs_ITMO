package integration.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import trigonometric.Cos;
import trigonometric.Sin;

public class CosTest {
    private Sin sinMock;
    private Cos cos;
    private static final double PRECISION = 1e-10;

    @BeforeEach
    void initMock() {
        sinMock = mock(Sin.class);

        when(sinMock.calculate(0.0)).thenReturn(0.0);
        when(sinMock.calculate(Math.PI)).thenReturn(0.0);
        when(sinMock.calculate(2 * Math.PI)).thenReturn(0.0);
        when(sinMock.calculate(-Math.PI)).thenReturn(0.0);

        when(sinMock.calculate(Math.PI / 2)).thenReturn(1.0);
        when(sinMock.calculate(5 * Math.PI / 2)).thenReturn(1.0);
        when(sinMock.calculate(-3 * Math.PI / 2)).thenReturn(1.0);

        when(sinMock.calculate(3 * Math.PI / 2)).thenReturn(-1.0);
        when(sinMock.calculate(-Math.PI / 2)).thenReturn(-1.0);

        cos = new Cos(sinMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            Math.PI,
            2 * Math.PI,
            -Math.PI,
            Math.PI / 2,
            5 * Math.PI / 2,
            -3 * Math.PI / 2,
            3 * Math.PI / 2,
            -Math.PI / 2,
    })
    public void testCosMockValues(double x) {
        double expected = Math.cos(x);
        double actual = cos.calculate(x);
        assertEquals(expected, actual, PRECISION);
        verify(sinMock, times(1)).calculate(x);
    }
}
