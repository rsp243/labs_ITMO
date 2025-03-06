package task1;

import static task1.Taylor.expandSinFunc;

public class Trigonometric {
    private static final double PRECISION = 1e-10;
    
    public static double getSinX(double x) {
        double result = expandSinFunc(x, PRECISION);

        return result;
    }

    public static double getCscX(double x) {
        double result = 1 / getSinX(x);

        return result;
    }

    public static double getCosX(double x) {
        double result = Math.sqrt(1 - Math.pow(getSinX(x), 2));

        return result;
    }

    public static double getSecX(double x) {
        double result = 1 / getCosX(x);

        return result;
    }

    public static double getTanX(double x) {
        double result = getSinX(x) / getCosX(x);

        return result;
    }

    public static double getCotX(double x) {
        double result = getCosX(x) / getSinX(x);

        return result;
    }
}
