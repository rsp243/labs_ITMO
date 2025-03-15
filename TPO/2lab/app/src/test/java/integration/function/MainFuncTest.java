package integration.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import function.LogFunc;
import function.MainFunc;
import function.TrigonometricFunc;

public class MainFuncTest {
    private LogFunc logFuncMock;
    private TrigonometricFunc trigonometricFuncMock;
    private MainFunc mainFunc; 
    
    private static final double PRECISION = 1e-8;

    @BeforeEach
    void initMock() {
        logFuncMock = mock(LogFunc.class);
        trigonometricFuncMock = mock(TrigonometricFunc.class);

        when(logFuncMock.calculate(2.919595)).thenReturn(0.0);
        when(trigonometricFuncMock.calculate(-6.86927)).thenReturn(0.0);

        mainFunc = new MainFunc(trigonometricFuncMock, logFuncMock);
    }

    @Test
    public void testMainFunction_Logarithmic() {
        double x = 2.919595;
        double expected = 0.0;
        double actual = mainFunc.calculate(x);

        assertEquals(expected, actual, PRECISION);

        verify(logFuncMock, times(1)).calculate(x);
        verify(trigonometricFuncMock, times(0)).calculate(x);
    }

    @Test
    public void testMainFunction_Trigonometric(){
        double x = -6.86927;
        double expected = 0.0;
        double actual = mainFunc.calculate(x);

        assertEquals(expected, actual, PRECISION);

        verify(logFuncMock, times(0)).calculate(x);
        verify(trigonometricFuncMock, times(1)).calculate(x);
    }
}
