package function;

import trigonometric.Cot;
import trigonometric.Csc;
import trigonometric.Sec;
import trigonometric.Sin;
import trigonometric.Tan;

public class TrigonometricFunc implements CalculatedFunction {
    private Tan tan;
    private Csc csc;
    private Cot cot;
    private Sec sec;
    private Sin sin;

    public TrigonometricFunc(Tan tan, Csc csc, Cot cot, Sec sec, Sin sin) {
        this.tan = tan;
        this.csc = csc;
        this.cot = cot;
        this.sec = sec;
        this.sin = sin;
    }

    public double calculate(double x) {
        double result = Math.pow(
                ((((tan.calculate(x) / csc.calculate(x)) / (cot.calculate(x) + sec.calculate(x))) + sec.calculate(x))
                        * sin.calculate(x)),
                3);

        return result;
    }
}
