package src.interfaces;

import src.classes.PersonGroup;
import src.enums.Controller;

public interface GroupForming {
    public Controller getInTheGroup(PersonGroup personGroupObj);
    public Controller getOutTheGroup(PersonGroup personGroupObj);
}
