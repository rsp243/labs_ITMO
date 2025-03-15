package unit.function;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import function.LogFunc;
import function.MainFunc;
import function.TrigonometricFunc;
import log.Ln;
import log.Log10;
import log.Log2;
import log.Log3;
import log.Log5;
import tools.CsvExporter;
import trigonometric.Cos;
import trigonometric.Cot;
import trigonometric.Csc;
import trigonometric.Sec;
import trigonometric.Sin;
import trigonometric.Tan;

public class MainFuncTest {
    private Ln ln = new Ln();
    private Log2 log2 = new Log2(ln);
    private Log3 log3 = new Log3(ln);
    private Log5 log5 = new Log5(ln);
    private Log10 log10 = new Log10(ln);
    private LogFunc logFunc = new LogFunc(ln, log2, log3, log5, log10);

    private Sin sin = new Sin();
    private Cos cos = new Cos(sin);
    private Cot cot = new Cot(sin, cos);
    private Csc csc = new Csc(sin);
    private Sec sec = new Sec(cos);
    private Tan tan = new Tan(sin, cos);
    private TrigonometricFunc trigonometricFunc = new TrigonometricFunc(tan, csc, cot, sec, sin);

    private MainFunc mainFunc = new MainFunc(trigonometricFunc, logFunc);

    private static final double PRECISION = 1e-6;

    @Test
    public void exportCsv(){
        CsvExporter.export(mainFunc::calculate, -10.0, -0.1, 0.1, "main_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.5,
            1.5,
            2.0,
            4.0,
            6.0,
    })
    public void testMainFunctionLogarithmic(double x) {
        double expected = logFunc.calculate(x);
        double actual = mainFunc.calculate(x);

        assertEquals(expected, actual, PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            -2.0,
            -4.0,
            -6.0,
            -5.0,
            -10.0,
            -12.0,
            -250.0
    })
    public void testMainFunctionTrigonometric(double x) {
        double expected = trigonometricFunc.calculate(x);
        double actual = mainFunc.calculate(x);

        assertEquals(expected, actual, PRECISION);
    }
}
