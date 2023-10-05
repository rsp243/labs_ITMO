package utils;

import java.util.ArrayList;
import java.util.List;

public class ValuesCheck {
    private int xRangeStartValue;
    private int xRangeEndValue;
    private float yRangeStartValue;
    private float yRangeEndValue;
    private float rRangeStartValue;
    private float rRangeEndValue;

    public ValuesCheck(int xRangeStartValue, int xRangeEndValue, float yRangeStartValue, float yRangeEndValue,
            float rRangeStartValue, float rRangeEndValue) {
        this.xRangeStartValue = xRangeStartValue;
        this.xRangeEndValue = xRangeEndValue;
        this.yRangeStartValue = yRangeStartValue;
        this.yRangeEndValue = yRangeEndValue;
        this.rRangeStartValue = rRangeStartValue;
        this.rRangeEndValue = rRangeEndValue;
    }

    public ValuesCheck() {
        this(-3, 5, -3, 5, 1, 3);
    }

    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public boolean xyrCheck(String xStr, String yStr, String rStr) {
        if (isInt(xStr) && isFloat(yStr) && isFloat(rStr)) {
            int xVal = Integer.parseInt(xStr);
            float yVal = Float.parseFloat(yStr);
            float rVal = Float.parseFloat(rStr);
            List<Integer> xAvailibleValues = new ArrayList<>();
            for (int i = xRangeStartValue; i <= xRangeEndValue; i++) {
                xAvailibleValues.add(i);
            }
            List<Float> rAvailibleValues = new ArrayList<>();
            for (float i = rRangeStartValue; i < rRangeEndValue; i = i + (float) 0.5) {
                rAvailibleValues.add(i);
            }
            if (xAvailibleValues.contains(xVal) && yRangeStartValue < yVal && yVal < yRangeEndValue && rAvailibleValues.contains(rVal)) {
                return true;
            }
        }
        return false;
    }

    public int getxRangeStartValue() {
        return xRangeStartValue;
    }

    public void setxRangeStartValue(int xRangeStartValue) {
        this.xRangeStartValue = xRangeStartValue;
    }

    public int getxRangeEndValue() {
        return xRangeEndValue;
    }

    public void setxRangeEndValue(int xRangeEndValue) {
        this.xRangeEndValue = xRangeEndValue;
    }

    public float getyRangeStartValue() {
        return yRangeStartValue;
    }

    public void setyRangeStartValue(float yRangeStartValue) {
        this.yRangeStartValue = yRangeStartValue;
    }

    public float getyRangeEndValue() {
        return yRangeEndValue;
    }

    public void setyRangeEndValue(float yRangeEndValue) {
        this.yRangeEndValue = yRangeEndValue;
    }

    public float getrRangeStartValue() {
        return rRangeStartValue;
    }

    public void setrRangeStartValue(float rRangeStartValue) {
        this.rRangeStartValue = rRangeStartValue;
    }

    public float getrRangeEndValue() {
        return rRangeEndValue;
    }

    public void setrRangeEndValue(float rRangeEndValue) {
        this.rRangeEndValue = rRangeEndValue;
    }
}
