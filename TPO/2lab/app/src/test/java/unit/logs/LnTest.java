package unit.logs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import log.Ln;
import tools.CsvExporter;

public class LnTest {
    private Ln ln = new Ln();
    private static final double PRECISION = 1e-6;

    @Test
    public void exportCsv() {
        CsvExporter.export(ln::calculate, 1.1, 10, 0.1, "ln_result.csv", ";");
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {
            1.0,
            2.0,
            0.5,
            Math.E,
            10.0,
            0.1
    })
    public void testLnValues(double x) {
        double expected = Math.log(x);
        double actual = ln.calculate(x);

        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void testLnUndefinedAtZero() {
        assertThrows(ArithmeticException.class, () -> ln.calculate(0.0));
    }

    @Test
    public void testLnUndefinedAtNegative() {
        assertThrows(ArithmeticException.class, () -> ln.calculate(-1.0));
    }
}
