package unit.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tools.CsvExporter;
import trigonometric.Cos;
import trigonometric.Sin;

public class CosTest {
    private Sin sin = new Sin();
    private Cos cos = new Cos(sin);
    private static final double PRECISION = 1e-10;

    @Test
    public void exportCsv() {
        CsvExporter.export(cos::calculate, -5, 5, 0.1, "cos_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            Math.PI / 6,
            Math.PI,
            3 * Math.PI / 2,
            2 * Math.PI,
            -Math.PI / 6,
            -Math.PI / 2,
            1.0,
            -1.0
    })
    void testCos(double x) {
        double expected = Math.cos(x);
        double result = cos.calculate(x);

        assertEquals(expected, result, PRECISION);
    }
}
