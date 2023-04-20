package src.interfaces;

import src.classes.Message;
import src.classes.Person;
import src.classes.PersonGroup;
import src.enums.Controller;

public interface SpeakingAction {
    public Controller speakTo(Message message);
    public Controller speakTo(Message message, Person targetPerson);
    public Controller speakTo(Message message, PersonGroup tagetGroup);
}
