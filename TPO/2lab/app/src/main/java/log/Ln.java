package log;

public class Ln {
    private final double PRECISION = 1e-7;

    public double calculate(double x) {
        double result = expandLnFunc(x, PRECISION);

        return result;
    }

    private double expandLnFunc(double x, double precision) {
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
