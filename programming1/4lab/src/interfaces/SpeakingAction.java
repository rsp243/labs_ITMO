package src.interfaces;

import src.classes.Message;
import src.classes.Person;
import src.classes.PersonGroup;

public interface SpeakingAction {
    public boolean speakTo(Message message);
    public boolean speakTo(Message message, Person targetPerson);
    public boolean speakTo(Message message, PersonGroup tagetGroup);
}
