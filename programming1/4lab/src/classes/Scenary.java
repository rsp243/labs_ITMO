package src.classes;

import src.enums.Controller;
import src.interfaces.Performable;

public class Scenary implements Performable{
    private PersonGroup personGroupDoer; 
    private Person personDoer;
    private String subject;
    private String action;
    private Controller executionOfAction;
    private String effectAction;
    private int timeStart;
    private int timeDuration;

    public Scenary(PersonGroup aPersonGroup,
                    Person aPersonDoer,
                    String anAction, 
                    Controller anExecutionOfAction, 
                    String aSubjet, 
                    String aEffectAction, 
                    int aTimeStart, 
                    int aTimeDuration) {
        this.personGroupDoer = aPersonGroup;
        this.personDoer = aPersonDoer;
        this.action = anAction;
        this.subject = aSubjet;
        this.effectAction = aEffectAction;
        this.timeStart = aTimeDuration;
        this.timeDuration = aTimeDuration;
    }
    public PersonGroup getPersonGroupDoer() {
        return personGroupDoer;
    }

    public Person getPersonDoer() {
        return personDoer;
    }

    public String getAction() {
        return action;
    }

    public Controller getExecutionOfAction() {
        return executionOfAction;
    }

    public String getSubject() {
        return subject;
    }

    public String getEffectAction() {
        return effectAction;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    @Override
    public String execute() {
        return "Выполнение действия '" + this.action + "'.";
    }

}
