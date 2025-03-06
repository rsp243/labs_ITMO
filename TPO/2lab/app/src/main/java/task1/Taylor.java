package task1;

import java.math.BigDecimal;

public class Taylor {
    private static final BigDecimal PI = new BigDecimal("3.14159265358979323846");

    public static double expandSinFunc(double x, double precision) {
        double sum = 0;
        int n = 0;
        double current = x;

        while (Math.abs(current) >= precision) {
            sum += current;
            n++;

            current = Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / factorial(2 * n + 1);
        }
        return sum;
    }

    private static long factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static double expandLnFunc(double x, double precision) {
        if (x <= 0) {
            throw new ArithmeticException("Logarithm is not defined for non-positive numbers");
        }

        if (x > 2) {
            // Используем свойства логарифмов для уменьшения x до диапазона, в котором ряд сходится
            double lnX = expandLnFunc(x / 2, precision) + Math.log(2);
            return lnX;
        }

        double diff = x - 1;
        double sum = 0;
        double current = 1;
        int n = 1;

        while (Math.abs(current) > precision) {
            current = Math.pow(-1, n + 1) * Math.pow(diff, n) / n;
            sum += current;
            n++;
        }

        return sum;
    }
}
