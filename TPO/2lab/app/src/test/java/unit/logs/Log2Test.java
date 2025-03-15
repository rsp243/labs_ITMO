package unit.logs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import log.Ln;
import log.Log2;
import tools.CsvExporter;

public class Log2Test {
    private Ln ln = new Ln();
    private Log2 log2 = new Log2(ln);
    private static final double PRECISION = 1e-6;

    @Test
    public void exportCsv() {
        CsvExporter.export(log2::calculate, 1.1, 10, 0.1, "log2_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.0,
            2.0,
            0.5,
            4.0,
            8.0,
            3.0,
    })
    public void testLog2Values(double x) {
        double expected = Math.log(x) / Math.log(2);
        double actual = log2.calculate(x);
        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLog2UndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> log2.calculate(0.0));
    }

    @Test
    public void testLog2UndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> log2.calculate(-1.0));
    }
}
