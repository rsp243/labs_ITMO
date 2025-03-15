package trigonometric;

public class Sin {
    private static final double PRECISION = 1e-11;

    public double calculate(double x) {
        x = x % (2 * Math.PI);
        if (x < 0) {
            x += 2 * Math.PI;
        }

        double result = expandSinFunc(x, PRECISION);

        return result;
    }

    public static double expandSinFunc(double x, double precision) {
        double sum = 0;
        double current = x;
        int n = 0;

        while (Math.abs(current) >= precision) {
            sum += current;
            n++;

            current = current * (-1) * x * x / ((2 * n) * (2 * n + 1));
        }
        return sum;
    }
}
