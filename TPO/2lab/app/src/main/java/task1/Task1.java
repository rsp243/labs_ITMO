package task1;

import static task1.Logarithmic.getLnX;
import static task1.Logarithmic.getLog10X;
import static task1.Logarithmic.getLog2X;
import static task1.Logarithmic.getLog3X;
import static task1.Logarithmic.getLog5X;
import static task1.Trigonometric.getCotX;
import static task1.Trigonometric.getCscX;
import static task1.Trigonometric.getSecX;
import static task1.Trigonometric.getSinX;
import static task1.Trigonometric.getTanX;

public class Task1 {

    public static double solveTask(double x) {
        if (x > 0) {
            return solveLogarithmExpr(x);
        }
        
        return solveTrigonometricExpr(x);
    }

    public static double solveTrigonometricExpr(double x) {
        if (x > 0) {
            throw new IllegalArgumentException("Trigonometric expression only applicable for x <= 0");
        }
        double result = Math.pow(((((getTanX(x) / getCscX(x)) / (getCotX(x) + getSecX(x))) + getSecX(x)) * getSinX(x)),
                3);

        return result;
    }

    public static double solveLogarithmExpr(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("Trigonometric expression only applicable for x > 0");
        }
        double result = (((((getLnX(x) - getLog2X(x)) - Math.pow(getLog3X(x), 3)) * getLnX(x)) * getLog10X(x)) + (getLog10X(x) / getLog5X(x)));

        return result;
    }
}
