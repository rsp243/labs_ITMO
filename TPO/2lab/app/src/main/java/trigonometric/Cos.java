package trigonometric;

public class Cos {
    private Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double calculate(double x) {
        double result = Math.sqrt(1 - Math.pow(sin.calculate(x), 2));

        x = x % (2 * Math.PI);
        if (x < 0) {
            x += 2 * Math.PI;
        }

        if ((x > Math.PI / 2 && x < 3 * Math.PI / 2)) {
            result = -result;
        }

        return result;
    }
}
