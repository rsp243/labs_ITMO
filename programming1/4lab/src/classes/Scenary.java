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
    private int timeDuration;

    public Scenary(Person aPersonDoer,
                    String anAction, 
                    Controller anExecutionOfAction, 
                    String aSubjet, 
                    Scenary anEffectAction, 
                    int aTimeDuration) {
        this.personGroupDoer = null;
        this.personDoer = aPersonDoer;
        this.action = anAction;
        this.executionOfAction = anExecutionOfAction;
        this.subject = aSubjet;
        this.effectAction = anEffectAction;
        this.timeDuration = aTimeDuration;
    }

    public Scenary(PersonGroup aPersonGroup,
                    String anAction, 
                    Controller anExecutionOfAction, 
                    String aSubjet, 
                    Scenary anEffectAction, 
                    int aTimeDuration) {
        this.personGroupDoer = aPersonGroup;
        this.personDoer = null;
        this.action = anAction;
        this.executionOfAction = anExecutionOfAction;
        this.subject = aSubjet;
        this.effectAction = anEffectAction;
        this.timeDuration = aTimeDuration;
    }
    public Scenary(Vehicle vehicle,
                    String anAction, 
                    Controller anExecutionOfAction, 
                    String aSubjet, 
                    Scenary anEffectAction, 
                    int aTimeDuration) {
        this.personGroupDoer = null;
        this.personDoer = null;
        this.vehicleDoer = vehicle;
        this.action = anAction;
        this.executionOfAction = anExecutionOfAction;
        this.subject = aSubjet;
        this.effectAction = anEffectAction;
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

    public int getTimeDuration() {
        return timeDuration;
    }

    @Override
    public String execute() throws TroubleWithDoerException {
        String resultSenarySentence = "";
        if (executionOfAction == Controller.FAILED) {
            String doer = (personGroupDoer == null && vehicleDoer == null) ? (personDoer.getName()) :
                ((personGroupDoer == null && personDoer == null) ? (vehicleDoer.getName()) :
                (personGroupDoer.getName()));
            resultSenarySentence = doer + " неудачно выполнил(-и) действие " + " '" + action + "'.";
        } else {
            String actionStr = (subject != null && subject != "") ? action + " объект <" + subject + ">" : action;
            String timeOfDoing = timeDuration != 0 ? "за " + timeDuration + " секунд" : "моментально";
            if ((personDoer != null && personGroupDoer != null && vehicleDoer != null) || (personDoer == null && personGroupDoer == null &&  vehicleDoer == null)) {
                throw new TroubleWithDoerException("Возникла ошибка со сценарием. Ошибка с исполнителем.");
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((personGroupDoer == null) ? 0 : personGroupDoer.hashCode());
        result = prime * result + ((personDoer == null) ? 0 : personDoer.hashCode());
        result = prime * result + ((vehicleDoer == null) ? 0 : vehicleDoer.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((executionOfAction == null) ? 0 : executionOfAction.hashCode());
        result = prime * result + ((effectAction == null) ? 0 : effectAction.hashCode());
        result = prime * result + timeDuration;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Scenary other = (Scenary) obj;
        if (personGroupDoer == null) {
            if (other.personGroupDoer != null)
                return false;
        } else if (!personGroupDoer.equals(other.personGroupDoer))
            return false;
        if (personDoer == null) {
            if (other.personDoer != null)
                return false;
        } else if (!personDoer.equals(other.personDoer))
            return false;
        if (vehicleDoer == null) {
            if (other.vehicleDoer != null)
                return false;
        } else if (!vehicleDoer.equals(other.vehicleDoer))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
        if (executionOfAction != other.executionOfAction)
            return false;
        if (effectAction == null) {
            if (other.effectAction != null)
                return false;
        } else if (!effectAction.equals(other.effectAction))
            return false;
        if (timeDuration != other.timeDuration)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Scenary [personGroupDoer=" + personGroupDoer + ", personDoer=" + personDoer + ", vehicleDoer="
                + vehicleDoer + ", subject=" + subject + ", action=" + action + ", executionOfAction="
                + executionOfAction + ", effectAction=" + effectAction + ", timeDuration=" + timeDuration + "]";
    }

}
