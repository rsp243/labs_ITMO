package src.data.classes;

import java.time.LocalDate;

import src.data.enums.OrganisationType;
import src.fillers.Increment;

public class City {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null
    private int metersAboveSeaLevel;
    private int telephoneCode; //Значение поля должно быть больше 0, Максимальное значение поля: 100000
    private int carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private Climate climate; //Поле не может быть null
    private Human governor; //Поле может быть null

    public City(Increment uniqueID, String aName, Coordinates aCoordinates, Long anArea, Integer aPopulation, int aMetersAboveSeaLevel,
            int aTelephoneCode, int aCarCode, Climate aClimate, Human aGovernor) {
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
        creationDate = LocalDate.now();
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
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

    public int getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(int telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public int getCarCode() {
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
}