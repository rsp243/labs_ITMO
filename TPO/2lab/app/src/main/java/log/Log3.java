package log;

public class Log3 extends Log {
    public Log3(Ln ln) {
        super(ln);
    }

    public double calculate(double x) {
        return calculate(x, 3);
    }

}
