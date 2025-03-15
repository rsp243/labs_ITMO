package function;

public class MainFunc {
    private TrigonometricFunc trigonometricFunc;
    private LogFunc logFunc;

    public MainFunc(TrigonometricFunc trigonometricFunc, LogFunc logFunc) {
        this.trigonometricFunc = trigonometricFunc;
        this.logFunc = logFunc;
    }

    public double calculate(double x) {
        if (x > 0) {
            return logFunc.calculate(x);
        } else {
            return trigonometricFunc.calculate(x);
        }
    }
}
