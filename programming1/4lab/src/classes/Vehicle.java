package src.classes;

import java.util.Arrays;

import src.enums.Controller;
import src.enums.VehicleType;
import src.interfaces.MoveAction;

public class Vehicle implements MoveAction{
    private String name;
    private VehicleType vehicleType;
    private int countOfSeats;
    private Person[] passengers;
    private Coordinate currentCoordinates;
    private int speed;
     
    public Vehicle(String name, VehicleType vehicleType, int countOfSeats, Person[] passengers, Coordinate currentCoordinates, int speed) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.countOfSeats = countOfSeats;
        this.passengers = passengers;
        this.currentCoordinates = currentCoordinates;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public int getCountOfSeats() {
        return countOfSeats;
    }
    public Person[] getPassengers() {
        return passengers;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setPassengers(Person[] passangers) {
        this.passengers = passangers;
    }
    public Coordinate getCurrentCoordinates() {
        return currentCoordinates;
    }
    @Override
    public Controller goTo(Location location) {
        return Controller.SUCCESSFULLY;
    }
    @Override
    public int getTimeGoing(Location location) {
        float distance = (float) (Math.pow(Math.pow(location.getCoordinates().getRightPosition() - currentCoordinates.getRightPosition(), 2)
            + Math.pow(location.getCoordinates().getTopPosition() - currentCoordinates.getTopPosition(), 2)
            + Math.pow(location.getCoordinates().getHeightPosition() - currentCoordinates.getHeightPosition(), 2), 0.5));
        currentCoordinates = location.getCoordinates();
        return (int) Math.ceil(400 * distance / speed / 1000);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((vehicleType == null) ? 0 : vehicleType.hashCode());
        result = prime * result + countOfSeats;
        result = prime * result + Arrays.hashCode(passengers);
        result = prime * result + speed;
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
        Vehicle other = (Vehicle) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (vehicleType != other.vehicleType)
            return false;
        if (countOfSeats != other.countOfSeats)
            return false;
        if (!Arrays.equals(passengers, other.passengers))
            return false;
        if (speed != other.speed)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vehicle [name=" + name + ", vehicleType=" + vehicleType + ", countOfSeats=" + countOfSeats
                + ", passengers=" + Arrays.toString(passengers) + ", speed=" + speed + "]";
    }
}
