package server.data.classes;

public class Coordinates implements Comparable<Coordinates> {
    private long x; //Значение поля должно быть больше -212
    private long y; //Значение поля должно быть больше -442  

    public Coordinates(long aX, long aY) {
        x = aX;
        y = aY;
    }

    // Comparable

    @Override
    public int compareTo(Coordinates o) {
        int result = 0;
        result = Long.compare(x, o.getX());
        if (result != 0) return result;
        result = Long.compare(y, o.getY());
        return result;
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
