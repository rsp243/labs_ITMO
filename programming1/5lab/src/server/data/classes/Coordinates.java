package src.server.data.classes;

public class Coordinates {
    private long x; //Значение поля должно быть больше -212
    private long y; //Значение поля должно быть больше -442  

    public Coordinates(long aX, long aY) {
        x = aX;
        y = aY;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates [x=" + x + ", y=" + y + "]";
    }
}
