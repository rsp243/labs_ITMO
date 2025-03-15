package unit.logs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import log.Ln;
import log.Log5;
import tools.CsvExporter;

public class Log5Test {
    private Ln ln = new Ln();
    private Log5 log5 = new Log5(ln);
    private static final double PRECISION = 1e-6;

    @Test
    public void exportCsv() {
        CsvExporter.export(log5::calculate, 1.1, 10, 0.1, "log5_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            6.0,
            125.0,
            25.0,
            0.5,
            5.0,
            1.0,
    })
    public void testLog5Values(double x) {
        double expected = Math.log(x) / Math.log(5);
        double actual = log5.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLog5UndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> log5.calculate(0.0));
    }

    @Test
    public void testLog5UndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> log5.calculate(-1.0));
    }
}
