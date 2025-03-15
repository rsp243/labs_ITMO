package log;

import function.CalculatedFunction;

public class Log2 extends Log implements CalculatedFunction {
    public Log2(Ln ln) {
        super(ln);
    }

    public double calculate(double x) {
        return calculate(x, 2);
    }
}
