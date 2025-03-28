package unit.trigonometric;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tools.CsvExporter;
import trigonometric.Sin;

public class SinTest {
    private Sin sin = new Sin();
    private static final double PRECISION = 1e-10;

    @Test
    public void exportCsv() {
        CsvExporter.export(sin::calculate, -5, 5, 0.1, "sin_result.csv", ";");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            Math.PI / 6,
            Math.PI / 2,
            Math.PI,
            3 * Math.PI / 2,
            2 * Math.PI,
            -Math.PI / 6,
            -Math.PI / 2,
            1.0,
            -1.0
    })
    void testSin(double x) {
        double expected = Math.sin(x);
        double result = sin.calculate(x);

        assertEquals(expected, result, PRECISION);
    }
}
