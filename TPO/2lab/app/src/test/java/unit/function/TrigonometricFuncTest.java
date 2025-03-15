package unit.function;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import function.TrigonometricFunc;
import tools.CsvExporter;
import trigonometric.Cos;
import trigonometric.Cot;
import trigonometric.Csc;
import trigonometric.Sec;
import trigonometric.Sin;
import trigonometric.Tan;

public class TrigonometricFuncTest {
        private Sin sin = new Sin();
        private Cos cos = new Cos(sin);
        private Cot cot = new Cot(sin, cos);
        private Csc csc = new Csc(sin);
        private Sec sec = new Sec(cos);
        private Tan tan = new Tan(sin, cos);
        private TrigonometricFunc trigonometricFunc = new TrigonometricFunc(tan, csc, cot, sec, sin);

        private static final double PRECISION = 1e-8;

        @Test
        public void exportCsv() {
                CsvExporter.export(trigonometricFunc::calculate, -10.0, -0.1, 0.1, "trigonometricfunc_result.csv", ";");
        }

        @ParameterizedTest
        @ValueSource(doubles = {
                        0.5,
                        3.0,
                        -2.0,
                        -4.0,
                        -6.0,
                        -5.0,
                        2.0
        })
        public void testLogFuncMockValues(double x) {
                double expected = Math.pow(
                                ((((Math.tan(x) / (1 / Math.sin(x))) / (Math.cos(x) / Math.sin(x) + 1 / Math.cos(x)))
                                                + 1 / Math.cos(x))
                                                * Math.sin(x)),
                                3);

                double actual = trigonometricFunc.calculate(x);
                assertEquals(expected, actual, PRECISION);
        }
}
