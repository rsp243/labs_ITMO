package log;

import function.CalculatedFunction;

public class Log10 extends Log implements CalculatedFunction {
    public Log10(Ln ln) {
        super(ln);
    }

    public double calculate(double x) {
        return calculate(x, 10);
    }
}
