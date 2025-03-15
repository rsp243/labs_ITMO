package unit.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tools.CsvExporter;
import trigonometric.Cos;
import trigonometric.Sin;
import trigonometric.Tan;

public class TanTest {
    private Sin sin = new Sin();
    private Cos cos = new Cos(sin);
    private Tan tan = new Tan(sin, cos);
    private static final double PRECISION = 1e-10;

    @Test
    public void exportCsv() {
        CsvExporter.export(tan::calculate, -5, 5, 0.1, "tan_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            Math.PI / 6,
            Math.PI,
            2 * Math.PI,
            -Math.PI / 6,
            1.0,
            -1.0
    })
    void testTan(double x) {
        double expected = Math.tan(x);
        double result = tan.calculate(x);

        assertEquals(expected, result, PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            3 * Math.PI / 2,
            -Math.PI / 2,
    })
    public void testTanUndefinedAtPiDividedBy2(double x) {
        assertThrows(ArithmeticException.class, () -> tan.calculate(x));
    }
}
