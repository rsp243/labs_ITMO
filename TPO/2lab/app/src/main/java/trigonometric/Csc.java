package trigonometric;

public class Csc {
    private Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public double calculate(double x) {
        double sinX = sin.calculate(x);

        if (sinX == 0) {
            throw new ArithmeticException("csc(x) is undefined when sin(x) = 0");
        }
        double result = 1 / sinX;

        return result;
    }
}
