package src.interfaces;

import src.classes.Person;
import src.classes.Vehicle;

public interface VehicleAction {
    public void getInTheCar(Person person, Vehicle vehicle);
    public void getOutTheCar(Person person, Vehicle vehicle);
}
