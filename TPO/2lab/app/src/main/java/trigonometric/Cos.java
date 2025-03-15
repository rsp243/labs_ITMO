package trigonometric;

public class Cos {
    private Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double calculate(double x) {
        double sinX = sin.calculate(x);

        double sinSquared = Math.pow(sinX, 2);
        if (sinSquared > 1.0) {
            sinSquared = 1.0;
        }

        double result = Math.sqrt(1 - sinSquared);

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
