package src.data.classes;

import src.data.enums.OrganisationType;
import src.fillers.Increment;

public class Organisation {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private String fullName; //Поле может быть null
    private OrganisationType type; //Поле может быть null
    private Address postalAddress; //Поле не может быть null

    public Organisation(Increment autoUniqueID, String aName, Coordinates aCoordinates, Double anAnnualTurnover, String aFullName, OrganisationType aType, Address aPostalAddress) {
        id = autoUniqueID.getNewId();
        name = aName;
        coordinates = aCoordinates;
        annualTurnover = anAnnualTurnover;
        fullName = aFullName;
        type = aType;
        postalAddress = aPostalAddress;
    }

    public int getId() {
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

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public Double getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Double annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public OrganisationType getType() {
        return type;
    }

    public void setType(OrganisationType type) {
        this.type = type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Override
    public String toString() {
        return "Organisation [id=" + id + ", name=" + name + ", coordinates=" + coordinates + ", creationDate="
                + creationDate + ", annualTurnover=" + annualTurnover + ", fullName=" + fullName + ", type=" + type
                + ", postalAddress=" + postalAddress + "]";
    }
}
