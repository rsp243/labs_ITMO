package unit.logs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import log.Ln;
import log.Log3;
import tools.CsvExporter;

public class Log3Test {
    private Ln ln = new Ln();
    private Log3 log3 = new Log3(ln);
    private static final double PRECISION = 1e-6;

    @Test
    public void exportCsv() {
        CsvExporter.export(log3::calculate, 1.1, 10, 0.1, "log3_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.0,
            3.0,
            0.5,
            9.0,
            27.0,
            4.0,
    })
    public void testLog3Values(double x) {
        double expected = Math.log(x) / Math.log(3);
        double actual = log3.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLog3UndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> log3.calculate(0.0));
    }

    @Test
    public void testLog3UndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> log3.calculate(-1.0));
    }
}
