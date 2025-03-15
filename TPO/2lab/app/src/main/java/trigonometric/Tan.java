package trigonometric;

public class Tan {
    private Sin sin;
    private Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public double calculate(double x) {
        double cosX = cos.calculate(x);

        if (cosX == 0) {
            throw new ArithmeticException("tan(x) is undefined when cos(x) = 0");
        }
        double result = sin.calculate(x) / cosX;

        return result;
    }
}
