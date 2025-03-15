package trigonometric;

public class Sec {
    private Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public double calculate(double x) {
        double cosX = cos.calculate(x);

        if (cosX == 0) {
            throw new ArithmeticException("sec(x) is undefined when cos(x) = 0");
        }
        double result = 1 / cosX;

        return result;
    }
}
