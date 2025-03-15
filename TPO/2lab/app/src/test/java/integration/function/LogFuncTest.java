package integration.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import function.LogFunc;
import log.Ln;
import log.Log10;
import log.Log2;
import log.Log3;
import log.Log5;

public class LogFuncTest {
    private Ln lnMock;
    private Log2 log2Mock;
    private Log3 log3Mock;
    private Log5 log5Mock;
    private Log10 log10Mock;
    private LogFunc logFunc;

    private static final double PRECISION = 1e-8;

    @BeforeEach
    void initMock() {
        lnMock = mock(Ln.class);
        log2Mock = mock(Log2.class);
        log3Mock = mock(Log3.class);
        log5Mock = mock(Log5.class);
        log10Mock = mock(Log10.class);

        when(lnMock.calculate(2.0)).thenReturn(Math.log(2));
        when(log2Mock.calculate(2.0)).thenReturn(1.0);
        when(log3Mock.calculate(2.0)).thenReturn(Math.log(2) / Math.log(3));
        when(log5Mock.calculate(2.0)).thenReturn(Math.log(2) / Math.log(5));
        when(log10Mock.calculate(2.0)).thenReturn(Math.log10(2));

        when(lnMock.calculate(4.0)).thenReturn(Math.log(4));
        when(log2Mock.calculate(4.0)).thenReturn(2.0);
        when(log3Mock.calculate(4.0)).thenReturn(Math.log(4) / Math.log(3));
        when(log5Mock.calculate(4.0)).thenReturn(Math.log(4) / Math.log(5));
        when(log10Mock.calculate(4.0)).thenReturn(Math.log10(4));

        when(lnMock.calculate(6.0)).thenReturn(Math.log(6));
        when(log2Mock.calculate(6.0)).thenReturn(Math.log(6) / Math.log(2));
        when(log3Mock.calculate(6.0)).thenReturn(Math.log(6) / Math.log(3));
        when(log5Mock.calculate(6.0)).thenReturn(Math.log(6) / Math.log(5));
        when(log10Mock.calculate(6.0)).thenReturn(Math.log10(6));

        logFunc = new LogFunc(lnMock, log2Mock, log3Mock, log5Mock, log10Mock);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            2.0,
            4.0,
            6.0,
    })
    public void testLogFuncMockValues(double x) {
        double expected = (((((Math.log(x) - Math.log(x) / Math.log(2)) - Math.pow(Math.log(x) / Math.log(3), 3)) * Math.log(x))
                * Math.log10(x)) + (Math.log10(x) / (Math.log(x) / Math.log(5))));

        double actual = logFunc.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

}
