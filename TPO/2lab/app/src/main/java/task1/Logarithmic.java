package task1;

import static task1.Taylor.expandLnFunc;

public class Logarithmic {
    private static final double PRECISION = 1e-7;
    
    public static double getLnX(double x) {
        double result = expandLnFunc(x, PRECISION);

        return result;
    }

    public static double getLogBaseX(double x, int base) { 
        if (x <= 0) {
            throw new ArithmeticException("Logarithm is not defined for non-positive numbers");
        }

        double lnX = getLnX(x);
        double lnBase = getLnX(base);

        return lnX / lnBase;
    }

    public static double getLog2X(double x) {
        return getLogBaseX(x, 2);
    }
    
    public static double getLog3X(double x) {
        return getLogBaseX(x, 3);
    }

    public static double getLog5X(double x) {
        return getLogBaseX(x, 5);
    }

    public static double getLog10X(double x) {
        return getLogBaseX(x, 10);
    }
}
