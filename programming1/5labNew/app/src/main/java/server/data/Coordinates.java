package server.data;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (x ^ (x >>> 32));
        result = prime * result + (int) (y ^ (y >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinates other = (Coordinates) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
