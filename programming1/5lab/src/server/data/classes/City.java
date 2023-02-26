package src.server.data.classes;

import java.util.Date;

import src.server.data.classes.Annotations.Complex;
import src.server.data.enums.Climate;
import src.server.fillers.Increment;

public class City {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Complex
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int area; //Значение поля должно быть больше 0, Поле не может быть null
    private int population; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer metersAboveSeaLevel;
    private long telephoneCode; //Значение поля должно быть больше 0, Максимальное значение поля: 100000
    private long carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    @Complex
    private Climate climate; //Поле не может быть null
    @Complex
    private Human governor; //Поле может быть null


    public City(Increment uniqueID, String aName, Coordinates aCoordinates, int anArea, int aPopulation, Integer aMetersAboveSeaLevel,
            long aTelephoneCode, long aCarCode, Climate aClimate, Human aGovernor) {
        id = uniqueID.getNewId();
        name = aName;
        coordinates = aCoordinates;
        area = anArea;
        population = aPopulation;
        metersAboveSeaLevel = aMetersAboveSeaLevel;
        telephoneCode = aTelephoneCode;
        carCode = aCarCode;
        climate = aClimate;
        governor = aGovernor;
        creationDate = new Date();
    }

    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public int getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(int metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public long getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(int telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public long getCarCode() {
        return carCode;
    }

    public void setCarCode(int carCode) {
        this.carCode = carCode;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", creationDate=" + creationDate
                + ", area=" + area + ", population=" + population + ", metersAboveSeaLevel=" + metersAboveSeaLevel
                + ", telephoneCode=" + telephoneCode + ", carCode=" + carCode + ", climate=" + climate + ", governor="
                + governor + "]";
    }    
}