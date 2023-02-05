package src.data.classes;

public class Location {
    private Double x; //Поле не может быть null
    private Float y; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 815, Поле может быть null

    public Location(Double aX, Float aY, String aName) {
        x = aX;
        y = aY;
        name = aName;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + ", name=" + name + "]";
    }
}
