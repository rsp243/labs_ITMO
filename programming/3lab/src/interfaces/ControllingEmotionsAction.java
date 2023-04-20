package src.interfaces;

import src.classes.Person;
import src.classes.PersonGroup;
import src.enums.Controller;
import src.enums.EmotionType;

public interface ControllingEmotionsAction {
    public Controller addEmotion(PersonGroup personGroup, EmotionType emotion);
    public Controller removeEmotion(PersonGroup personGroup, EmotionType emotion);
    public Controller addEmotion(Person person, EmotionType emotion);
    public Controller removeEmotion(Person person, EmotionType emotion);
    public Controller addEmotion(EmotionType emotion);
    public Controller removeEmotion(EmotionType emotion);
}
