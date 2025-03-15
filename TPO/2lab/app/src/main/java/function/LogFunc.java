package function;

import log.Ln;
import log.Log10;
import log.Log2;
import log.Log3;
import log.Log5;

public class LogFunc implements CalculatedFunction {
    private Ln ln;
    private Log2 log2;
    private Log3 log3;
    private Log5 log5;
    private Log10 log10;

    public LogFunc(Ln ln, Log2 log2, Log3 log3, Log5 log5, Log10 log10) {
        this.ln = ln;
        this.log2 = log2;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public double calculate(double x) {
        double result = (((((ln.calculate(x) - log2.calculate(x)) - Math.pow(log3.calculate(x), 3)) * ln.calculate(x))
                * log10.calculate(x)) + (log10.calculate(x) / log5.calculate(x)));

        return result;
    }
}
