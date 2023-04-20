package src.interfaces;

import src.classes.Person;
import src.classes.Vehicle;
import src.enums.Controller;

public interface VehicleAction {
    public Controller getInTheCar(Person person, Vehicle vehicle);
    public Controller getOutTheCar(Person person, Vehicle vehicle);
}
