package unit.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tools.CsvExporter;
import trigonometric.Csc;
import trigonometric.Sin;

public class CscTest {
    private Sin sin = new Sin();
    private Csc csc = new Csc(sin);
    private static final double PRECISION = 1e-10;

    @Test
    public void exportCsv() {
        CsvExporter.export(csc::calculate, -5, 5, 0.1, "csc_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            Math.PI / 6,
            3 * Math.PI / 2,
            -Math.PI / 6,
            -Math.PI / 2,
            1.0,
            -1.0
    })
    void testCsc(double x) {
        double expected = 1 / Math.sin(x);
        double result = csc.calculate(x);

        assertEquals(expected, result, PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            2 * Math.PI
    })
    public void testCscUndefinedAt2Pi(double x) {
        assertThrows(ArithmeticException.class, () -> csc.calculate(x));
    }
}
