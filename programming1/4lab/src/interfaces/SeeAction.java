package src.interfaces;

import src.classes.Creature;
import src.classes.Location;
import src.classes.PersonGroup;
import src.classes.Vehicle;

public interface SeeAction { 
    public Creature See(Creature seeingCreature);
    public PersonGroup See(PersonGroup seeingGroup);
    public Location See(Location seeingLocation);
    public Vehicle See(Vehicle seeingVehicle);
}
