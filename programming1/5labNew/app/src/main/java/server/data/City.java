package server.data;

import java.util.ArrayList;
import java.util.Date;

import server.data.Annotations.Complex;
import server.data.enums.Climate;

public class City implements Comparable<City> {
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


    public City(Long uniqueID, String aName, Coordinates aCoordinates, int anArea, int aPopulation, Integer aMetersAboveSeaLevel,
            long aTelephoneCode, long aCarCode, Climate aClimate, Human aGovernor) {
        id = uniqueID;
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

    public ArrayList<String> getAllFieldsValues() {
        ArrayList<String> resultArrayList = new ArrayList<>();
        resultArrayList.add(Long.toString(id));
        resultArrayList.add(name);
        resultArrayList.add(Long.toString(coordinates.getX()));
        resultArrayList.add(Long.toString(coordinates.getY()));
        resultArrayList.add(creationDate.toString());
        resultArrayList.add(Long.toString(area));
        resultArrayList.add(Long.toString(population));
        resultArrayList.add(Long.toString(metersAboveSeaLevel));
        resultArrayList.add(Long.toString(telephoneCode));
        resultArrayList.add(Long.toString(carCode));
        resultArrayList.add(climate.toString());
        resultArrayList.add(governor.getAge().toString());
        resultArrayList.add(governor.getHeight().toString());
        resultArrayList.add(governor.getBirthday().toString());
        return resultArrayList;
    }

    // Comparable

    @Override
    public int compareTo(City o) {
        int result = 0;
        result = name.compareTo(o.getName());
        if (result != 0) return result;
        result = coordinates.compareTo(o.getCoordinates());
        if (result != 0) return result;
        result = Integer.compare(area, o.getArea());
        if (result != 0) return result;
        result = Integer.compare(population, o.getPopulation());
        if (result != 0) return result;
        result = Integer.compare(metersAboveSeaLevel, o.getMetersAboveSeaLevel());
        if (result != 0) return result;
        result = Long.compare(telephoneCode, o.getTelephoneCode());
        if (result != 0) return result;
        result = Long.compare(carCode, o.getCarCode());
        if (result != 0) return result;
        result = climate.compareTo(o.getClimate());
        if (result != 0) return result;
        result = governor.compareTo(o.getGovernor());
        return 0;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setMetersAboveSeaLevel(Integer metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public void setTelephoneCode(long telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public void setCarCode(long carCode) {
        this.carCode = carCode;
    }    
    
    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", creationDate=" + creationDate
                + ", area=" + area + ", population=" + population + ", metersAboveSeaLevel=" + metersAboveSeaLevel
                + ", telephoneCode=" + telephoneCode + ", carCode=" + carCode + ", climate=" + climate + ", governor="
                + governor + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + area;
        result = prime * result + population;
        result = prime * result + ((metersAboveSeaLevel == null) ? 0 : metersAboveSeaLevel.hashCode());
        result = prime * result + (int) (telephoneCode ^ (telephoneCode >>> 32));
        result = prime * result + (int) (carCode ^ (carCode >>> 32));
        result = prime * result + ((climate == null) ? 0 : climate.hashCode());
        result = prime * result + ((governor == null) ? 0 : governor.hashCode());
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
        City other = (City) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (coordinates == null) {
            if (other.coordinates != null)
                return false;
        } else if (!coordinates.equals(other.coordinates))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (area != other.area)
            return false;
        if (population != other.population)
            return false;
        if (metersAboveSeaLevel == null) {
            if (other.metersAboveSeaLevel != null)
                return false;
        } else if (!metersAboveSeaLevel.equals(other.metersAboveSeaLevel))
            return false;
        if (telephoneCode != other.telephoneCode)
            return false;
        if (carCode != other.carCode)
            return false;
        if (climate != other.climate)
            return false;
        if (governor == null) {
            if (other.governor != null)
                return false;
        } else if (!governor.equals(other.governor))
            return false;
        return true;
    }
}