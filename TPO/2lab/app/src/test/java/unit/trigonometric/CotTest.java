package unit.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tools.CsvExporter;
import trigonometric.Cos;
import trigonometric.Cot;
import trigonometric.Sin;

public class CotTest {
    private Sin sin = new Sin();
    private Cos cos = new Cos(sin);
    private Cot cot = new Cot(sin, cos);
    private static final double PRECISION = 1e-10;

    @Test
    public void exportCsv() {
        CsvExporter.export(cot::calculate, -5, 5, 0.1, "cot_result.csv", ";");
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
    void testCot(double x) {
        double expected = 1 / Math.tan(x);
        double result = cot.calculate(x);

        assertEquals(expected, result, PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            2 * Math.PI
    })
    public void testCotUndefinedAt2Pi(double x) {
        assertThrows(ArithmeticException.class, () -> cot.calculate(x));
    }
}
