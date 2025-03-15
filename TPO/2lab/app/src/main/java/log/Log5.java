package log;

import function.CalculatedFunction;

public class Log5 extends Log implements CalculatedFunction {
    public Log5(Ln ln) {
        super(ln);
    }

    public double calculate(double x) {
        return calculate(x, 5);
    }
}
