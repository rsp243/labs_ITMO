package task1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static task1.Taylor.expandArctgFunc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Task1Test {
    private static final double PRECISION = 1e-10;
    private static final int TERMS = 30;

    @Test
    @DisplayName("arctg(0)")
    void testArctg0() {
        double x = 0;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }

    @Test
    @DisplayName("arctg(1.5)")
    void testArctg1() {
        double x = 1.5;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }

    @Test
    @DisplayName("arctg(-1.5)")
    void testArctgMinus1() {
        double x = -1.5;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }

    @Test
    @DisplayName("arctg(0.5)")
    void testArctgHalh() {
        double x = 0.5;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }

    @Test
    @DisplayName("arctg(-0.5)")
    void testArctgMinusHalh() {
        double x = -0.5;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }
    
    @Test
    @DisplayName("arctg(2)")
    void testArctg2() {
        double x = 2;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }
    
    @Test
    @DisplayName("arctg(-2)")
    void testArctgMinux2() {
        double x = -2;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }

    @Test
    @DisplayName("arctg(52)")
    void testArctgM52() {
        double x = 52;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }

    @Test
    @DisplayName("arctg(-52)")
    void testArctgMinux52() {
        double x = -52;
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }

    @Test
    @DisplayName("Decreasing error with n is increasing")
    void testError() {
        double x = 0.5;
        double prevError = Double.MAX_VALUE;
        for (int n = 2; n <= 10; n++) {
            double result = expandArctgFunc(x, n);
            double error = Math.abs(result - Math.atan(x));
            assertTrue(error < prevError);
            prevError = error;
        }
    }
    
    @ParameterizedTest
    @ValueSource(doubles =  {5, 2, 3})
    void testArctgArg(double x) {
        double expected = Math.atan(x);
        double result = expandArctgFunc(x, TERMS);
        System.out.println("Result: " + result + "; Expected result: " + expected);
        assertEquals(expected, result, PRECISION);
    }
}
