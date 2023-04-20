package src.interfaces;

import src.classes.Location;
import src.enums.Controller;

public interface MoveAction {
    public Controller goTo(Location location);
    public int getTimeGoing(Location location);
}
