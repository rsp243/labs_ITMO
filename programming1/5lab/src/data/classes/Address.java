package src.data.classes;

public class Address {
    private String street; //Длина строки не должна быть больше 157, Поле не может быть null
    private Location town; //Поле может быть null

    public Address(String aStreet, Location aTown) { 
        street = aStreet;
        town = aTown;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Location getTown() {
        return town;
    }

    public void setTown(Location town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", town=" + town + "]";
    }
    
}