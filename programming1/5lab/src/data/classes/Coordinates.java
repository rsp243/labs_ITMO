package src.data.classes;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private int y; //Значение поля должно быть больше -598

    public Coordinates(Integer aX, int aY) {
        x = aX;
        y = aY;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates [x=" + x + ", y=" + y + "]";
    }
}
