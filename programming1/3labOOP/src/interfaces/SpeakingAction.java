package src.interfaces;

import src.classes.Message;
import src.classes.Person;

public interface SpeakingAction {
    public boolean speakTo(Message messageObj, Person targetPerson);
}
