package unit.function;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import function.LogFunc;
import log.Ln;
import log.Log10;
import log.Log2;
import log.Log3;
import log.Log5;
import tools.CsvExporter;

public class LogFuncTest {
    private Ln ln = new Ln();
    private Log2 log2 = new Log2(ln);
    private Log3 log3 = new Log3(ln);
    private Log5 log5 = new Log5(ln);
    private Log10 log10 = new Log10(ln);
    private LogFunc logFunc = new LogFunc(ln, log2, log3, log5, log10);

    private static final double PRECISION = 1e-6;

    @Test
    public void exportCsv() {
            CsvExporter.export(logFunc::calculate, 1.1, 10, 0.1, "logfunc_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.5,
            1.5,
            2.0,
            4.0,
            6.0,
    })
    public void testLogFuncValues(double x) {
        double expected = (((((Math.log(x) - Math.log(x) / Math.log(2)) - Math.pow(Math.log(x) / Math.log(3), 3)) * Math.log(x))
                * Math.log10(x)) + (Math.log10(x) / (Math.log(x) / Math.log(5))));

        double actual = logFunc.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }
}
