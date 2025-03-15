package trigonometric;

public class Cot {
    private Sin sin;
    private Cos cos;

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public double calculate(double x) {
        double sinX = sin.calculate(x);
        double result = cos.calculate(x) / sinX;

        if (sinX == 0) {
            throw new ArithmeticException("cot(x) is undefined when sin(x) = 0");
        }

        return result;
    }
}
