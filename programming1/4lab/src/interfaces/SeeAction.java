package src.interfaces;

import src.classes.Creature;
import src.classes.Location;
import src.classes.PersonGroup;
import src.classes.Vehicle;
import src.enums.Controller;

public interface SeeAction { 
    public Controller see(Creature seeingCreature);
    public Controller see(PersonGroup seeingGroup);
    public Controller see(Location seeingLocation);
    public Controller see(Vehicle seeingVehicle);
}
