package src.classes;

import java.util.Arrays;

import src.enums.Controller;
import src.enums.Freedom;
import src.interfaces.AbleToSleep;
import src.interfaces.DetainAction;
import src.interfaces.MoveAction;
import src.interfaces.SpeakingAction;

public class PersonGroup implements DetainAction, MoveAction, AbleToSleep, SpeakingAction{ 
    private String name;
    private Person[] participants;
    private int countOfParticipants;
    private Coordinate currentCoordinate;
    
    public PersonGroup(String name, Person[] participants, int countOfParticipants) {
        this.name = name;
        this.participants = participants;
        this.countOfParticipants = countOfParticipants;
    }

	public String getName() {
        return name;
    }
    public String getParticipants() {
        String[] arrayOfNamedParticipants = new String[participants.length];
        int position = 0;
        for (Person participant : participants) {
            if (participant != null) {
                arrayOfNamedParticipants[position] = participant.getName();
            } else {
                arrayOfNamedParticipants[position] = "null";
            }
            position += 1;
        }
        return Arrays.toString(arrayOfNamedParticipants);
    }
    public int getCountOfParticipants() {
        return countOfParticipants;
    }
    public Coordinate getCurrentCoordinate() {
        return currentCoordinate;
    }

    @Override
    public Controller Sleep(Location location) {
        return Controller.SUCCESSFULLY;
    }
    @Override
    public float goTo(Location location) {
        float[] arrayOfTimesOnDistance = new float[countOfParticipants];
        int position = 0;
        for (Person participant : participants) {
            arrayOfTimesOnDistance[position] = participant.goTo(location); 
            position++;
        } 
        float maxTime = 0;
        Arrays.sort(arrayOfTimesOnDistance);
        if (countOfParticipants != 0) {
            maxTime = arrayOfTimesOnDistance[countOfParticipants - 1];
        }
        currentCoordinate = location.getCoordinates();
        return maxTime;
  
    }
    @Override
    public Controller ControlFreedom(Person targetPerson, Freedom freedom) {
        if (countOfParticipants > 1) {
            targetPerson.setFreedom(freedom);
            return Controller.SUCCESSFULLY;
        } else {
            if (Math.random() < 0.5) {
                return Controller.FAILED;
            } else {
                targetPerson.setFreedom(freedom);
                return Controller.SUCCESSFULLY;
            }
        }
    }
    @Override
    public Controller ControlFreedom(PersonGroup targetGroup, Freedom freedom) {
        if (countOfParticipants > targetGroup.countOfParticipants) {
            for (Person participant : participants) {
                if (participant != null) {
                    participant.setFreedom(freedom);
                }
            }
            return Controller.SUCCESSFULLY;
        } else {
            if (Math.random() < 0.5) {
                return Controller.FAILED;
            } else {
                for (Person participant : participants) {
                    if (participant != null) {
                        participant.setFreedom(freedom);
                    }
                }
                return Controller.SUCCESSFULLY;
            }
        }
    }
    @Override
    public boolean speakTo(Message messageObj, Person targetPerson) {
        return messageObj.getIsMessageTruly();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + Arrays.hashCode(participants);
        result = prime * result + countOfParticipants;
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
        PersonGroup other = (PersonGroup) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (!Arrays.equals(participants, other.participants))
            return false;
        if (countOfParticipants != other.countOfParticipants)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PersonGroup [name=" + name + ", participants=" + Arrays.toString(participants)
                + ", countOfParticipants=" + countOfParticipants + "]";
    }
}
