package log;

public class Log {
    private Ln ln;

    public Log(Ln ln) {
        this.ln = ln;
    }

    public double calculate(double x, int base) { 
        if (x <= 0) {
            throw new ArithmeticException("Logarithm is not defined for non-positive numbers");
        }

        double lnX = ln.calculate(x);
        double lnBase = ln.calculate(base);

        return lnX / lnBase;
    }
}
