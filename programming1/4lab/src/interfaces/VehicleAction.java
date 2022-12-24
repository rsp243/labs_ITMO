package src.interfaces;

import src.classes.Vehicle;
import src.enums.Controller;

public interface VehicleAction {
    public Controller getInTheVehicle(Vehicle vehicle);
    public Controller getOutTheVehicle(Vehicle vehicle);
}
