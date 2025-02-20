package task1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Taylor {
    private static final BigDecimal PI = new BigDecimal("3.14159265358979323846");

    public static double expandArctgFunc(double x, int n) {

        if (Math.abs(x) > 1) {
            // Use range reduction for |x| > 1

            if (x < 0) {
                return -1 * expandArctgFunc(-1 * x, n);
            }
            double reducedX = 1 / x;
            double arctanReducedX = expandArctgFunc(reducedX, n);
            return Math.signum(x) * (PI.divide(new BigDecimal("2"), RoundingMode.HALF_UP).doubleValue()
                   - arctanReducedX);
        }

        BigDecimal result = BigDecimal.ZERO;
        BigDecimal xBig = new BigDecimal(Double.toString(x));

        for (int i = 0; i < n; i++) {
            BigDecimal term = xBig.pow(2 * i + 1)
                    .divide(new BigDecimal(2 * i + 1), 20, RoundingMode.HALF_UP);
            if (i % 2 == 0) {
                result = result.add(term);
            } else {
                result = result.subtract(term);
            }
        }

        return result.doubleValue();
    }
}
