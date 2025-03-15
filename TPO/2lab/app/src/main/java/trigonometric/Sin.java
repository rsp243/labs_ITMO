package trigonometric;

public class Sin {
    private static final double PRECISION = 1e-10;

    public double calculate(double x) {
        double result = expandSinFunc(x, PRECISION);

        return result;
    }

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
}
