package src.classes;

import src.enums.Controller;
import src.interfaces.Performable;

public class Scenary implements Performable{
    private PersonGroup personGroupDoer; 
    private Person personDoer;
    private Vehicle vehicleDoer;
    private String subject;
    private String action;
    private Controller executionOfAction;
    private Scenary effectAction;
    private int timeStart;
    private int timeDuration;

    public Scenary(Person aPersonDoer,
                    String anAction, 
                    Controller anExecutionOfAction, 
                    String aSubjet, 
                    Scenary anEffectAction, 
                    int aTimeStart, 
                    int aTimeDuration) {
        this.personGroupDoer = null;
        this.personDoer = aPersonDoer;
        this.action = anAction;
        this.executionOfAction = anExecutionOfAction;
        this.subject = aSubjet;
        this.effectAction = anEffectAction;
        this.timeStart = aTimeDuration;
        this.timeDuration = aTimeDuration;
    }

    public Scenary(PersonGroup aPersonGroup,
                    String anAction, 
                    Controller anExecutionOfAction, 
                    String aSubjet, 
                    Scenary anEffectAction, 
                    int aTimeStart, 
                    int aTimeDuration) {
        this.personGroupDoer = aPersonGroup;
        this.personDoer = null;
        this.action = anAction;
        this.executionOfAction = anExecutionOfAction;
        this.subject = aSubjet;
        this.effectAction = anEffectAction;
        this.timeStart = aTimeDuration;
        this.timeDuration = aTimeDuration;
    }
    public Scenary(Vehicle vehicle,
                    String anAction, 
                    Controller anExecutionOfAction, 
                    String aSubjet, 
                    Scenary anEffectAction, 
                    int aTimeStart, 
                    int aTimeDuration) {
        this.personGroupDoer = null;
        this.personDoer = null;
        this.vehicleDoer = vehicle;
        this.action = anAction;
        this.executionOfAction = anExecutionOfAction;
        this.subject = aSubjet;
        this.effectAction = anEffectAction;
        this.timeStart = aTimeDuration;
        this.timeDuration = aTimeDuration;
    }

    public PersonGroup getPersonGroupDoer() {
        return personGroupDoer;
    }

    public Person getPersonDoer() {
        return personDoer;
    }

    public Vehicle getVehicleDoer() {
        return vehicleDoer;
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

    public Scenary getEffectAction() {
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
        String resultSenarySentence = "";
        if (executionOfAction == Controller.FAILED) {
            resultSenarySentence = personDoer.getName() + " неудачно выполнил действие " + " '" + action + "'.";
        } else {
            String actionStr = (subject != null && subject != "") ? action + " объект <" + subject + ">" : action;
            String timeOfDoing = timeDuration != 0 ? "за " + timeDuration + " секунд" : "моментально";
            if ((personDoer != null && personGroupDoer != null && vehicleDoer != null) || (personDoer == null && personGroupDoer == null &&  vehicleDoer == null)) {
                resultSenarySentence = "Возникла ошибка со сценарием. Ошибка с исполнителем.";
            } else {
                String doer = (personGroupDoer == null && vehicleDoer == null) ? (personDoer.getName()) :
                        ((personGroupDoer == null && personDoer == null) ? (vehicleDoer.getName()) :
                        (personGroupDoer.getName()));
                String effectStr;
                if (effectAction != null) {effectStr = "И поэтому " + effectAction.execute();} 
                else {effectStr = "";}
                 resultSenarySentence = doer + " выполнил(-и) действие '" + actionStr + "' " + timeOfDoing + ". " + effectStr;  
            }
        }
        return resultSenarySentence;
    }

}
