package unit.logs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import log.Ln;
import log.Log10;
import tools.CsvExporter;

public class Log10Test {
    private Ln ln = new Ln();
    private Log10 log10 = new Log10(ln);
    private static final double PRECISION = 1e-6;

    @Test
    public void exportCsv() {
        CsvExporter.export(log10::calculate, 1.1, 10, 0.1, "log10_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.0,
            10.0,
            0.5,
            100.0,
            1000.0,
            11.0,
    })
    public void testLog10Values(double x) {
        double expected = Math.log(x) / Math.log(10);
        double actual = log10.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLog10UndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> log10.calculate(0.0));
    }

    @Test
    public void testLog10UndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> log10.calculate(-1.0));
    }
}
