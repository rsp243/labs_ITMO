package src.interfaces;

import src.classes.Message;
import src.classes.Person;
import src.classes.PersonGroup;

public interface SpeakingAction {
    public boolean speakTo(Message messageObj);
    public boolean speakTo(Message messageObj, Person targetPerson);
    public boolean speakTo(Message messageObj, PersonGroup tagetGroup);
}
