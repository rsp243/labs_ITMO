package model;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.abs;

public class AreaResultChecker {
    private static boolean checkCircle(final double xValue, final double yValue, final double rValue){
        return xValue > 0 && yValue > 0 && sqrt(pow(xValue, 2) + pow(yValue, 2)) <= rValue;
    }

    // Maybe errors with isHit value
    private static boolean checkTriangle(final double xValue, final double yValue, final double rValue){
        return xValue <= 0 && yValue <= 0 && abs(xValue) + abs(yValue) / 2 <= rValue / 2;
    }

    private static boolean checkRectangle(final double xValue, final double yValue, final double rValue){
        return xValue <= 0 && yValue >= 0 && abs(xValue) <= rValue && yValue <= rValue;
    }

    private static boolean checkArea(final double xValue, final double yValue, final double rValue) {
        boolean inCircle = checkCircle(xValue, yValue, rValue);
        boolean inTriangle = checkTriangle(xValue, yValue, rValue);
        boolean inReсtangle = checkRectangle(xValue, yValue, rValue); 
        return inCircle || inTriangle || inReсtangle;
    }

    private static boolean validateXYR(double x, double y, double r) {
        return x >= -5 && x <= 3 && y >= -5 && y <= 5 && r >= 1 && r <= 3;
    }
    public static boolean getResult(double x, double y, double r) {
        return validateXYR(x, y, r) && checkArea(x, y, r);
    }
}