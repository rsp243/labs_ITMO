package unit.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tools.CsvExporter;
import trigonometric.Cos;
import trigonometric.Sec;
import trigonometric.Sin;

public class SecTest {
    private Sin sin = new Sin();
    private Cos cos = new Cos(sin);
    private Sec sec = new Sec(cos);
    private static final double PRECISION = 1e-10;

    @Test
    public void exportCsv() {
        CsvExporter.export(sec::calculate, -5, 5, 0.1, "sec_result.csv", ";");
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
    void testSec(double x) {
        double expected = 1 / Math.cos(x);
        double result = sec.calculate(x);

        assertEquals(expected, result, PRECISION);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            3 * Math.PI / 2,
            -Math.PI / 2,
    })
    public void testSecUndefinedAtPiDividedBy2(double x) {
        assertThrows(ArithmeticException.class, () -> sec.calculate(x));
    }
}
