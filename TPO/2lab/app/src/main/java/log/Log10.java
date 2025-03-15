package log;

public class Log10 extends Log {
    public Log10(Ln ln) {
        super(ln);
    }

    public double calculate(double x) {
        return calculate(x, 10);
    }
}
