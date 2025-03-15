package log;

public class Log2 extends Log {
    public Log2(Ln ln) {
        super(ln);
    }

    public double calculate(double x) {
        return calculate(x, 2);
    }
}
