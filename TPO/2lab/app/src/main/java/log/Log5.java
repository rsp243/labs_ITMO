package log;

public class Log5 extends Log {
    public Log5(Ln ln) {
        super(ln);
    }

    public double calculate(double x) {
        return calculate(x, 5);
    }
}
