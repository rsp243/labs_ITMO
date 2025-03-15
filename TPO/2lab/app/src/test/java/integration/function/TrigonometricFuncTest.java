package integration.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import function.TrigonometricFunc;
import trigonometric.Cot;
import trigonometric.Csc;
import trigonometric.Sec;
import trigonometric.Sin;
import trigonometric.Tan;

public class TrigonometricFuncTest {
    private Sin sinMock;
    private Cot cotMock;
    private Csc cscMock;
    private Sec secMock;
    private Tan tanMock;
    private TrigonometricFunc trigonometricFunc;

    private static final double PRECISION = 1e-8;

    @BeforeEach
    void initMock() {
        sinMock = mock(Sin.class);
        cotMock = mock(Cot.class);
        cscMock = mock(Csc.class);
        secMock = mock(Sec.class);
        tanMock = mock(Tan.class);

        when(tanMock.calculate(3.0)).thenReturn(Math.tan(3));
        when(secMock.calculate(3.0)).thenReturn(1 / Math.cos(3));
        when(cscMock.calculate(3.0)).thenReturn(1 / Math.sin(3));
        when(cotMock.calculate(3.0)).thenReturn(Math.cos(3) / Math.sin(3));
        when(sinMock.calculate(3.0)).thenReturn(Math.sin(3));

        when(tanMock.calculate(-5.0)).thenReturn(Math.tan(-5));
        when(secMock.calculate(-5.0)).thenReturn(1 / Math.cos(-5));
        when(cscMock.calculate(-5.0)).thenReturn(1 / Math.sin(-5));
        when(cotMock.calculate(-5.0)).thenReturn(Math.cos(-5) / Math.sin(-5));
        when(sinMock.calculate(-5.0)).thenReturn(Math.sin(-5));

        when(tanMock.calculate(2.0)).thenReturn(Math.tan(2));
        when(secMock.calculate(2.0)).thenReturn(1 / Math.cos(2));
        when(cscMock.calculate(2.0)).thenReturn(1 / Math.sin(2));
        when(cotMock.calculate(2.0)).thenReturn(Math.cos(2) / Math.sin(2));
        when(sinMock.calculate(2.0)).thenReturn(Math.sin(2));

        trigonometricFunc = new TrigonometricFunc(tanMock, cscMock, cotMock, secMock, sinMock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            3.0,
            -5.0,
            2.0
    })
    public void testLogFuncMockValues(double x) {
        double expected = Math.pow(
                ((((Math.tan(x) / (1 / Math.sin(x))) / (Math.cos(x) / Math.sin(x) + 1 / Math.cos(x))) + 1 / Math.cos(x))
                        * Math.sin(x)),
                3);

        double actual = trigonometricFunc.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

}
