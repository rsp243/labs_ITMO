package log;

import function.CalculatedFunction;

public class Log3 extends Log implements CalculatedFunction {
    public Log3(Ln ln) {
        super(ln);
    }

    public double calculate(double x) {
        return calculate(x, 3);
    }

}
