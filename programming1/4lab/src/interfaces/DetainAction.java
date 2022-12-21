package src.interfaces;

import src.classes.Person;
import src.classes.PersonGroup;
import src.enums.Controller;
import src.enums.Freedom;

public interface DetainAction {
    public Controller ControlFreedom(Person targetPerson, Freedom freedom);
    public Controller ControlFreedom(PersonGroup targetGroup, Freedom freedom);   
}
