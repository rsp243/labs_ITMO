package src.classes;

import java.util.Arrays;

import src.enums.Controller;
import src.enums.Freedom;
import src.interfaces.AbleToSleep;
import src.interfaces.DetainAction;
import src.interfaces.GroupForming;
import src.interfaces.MoveAction;
import src.interfaces.SpeakingAction;
import src.interfaces.VehicleAction;

public class PersonGroup implements DetainAction, MoveAction, AbleToSleep, SpeakingAction, VehicleAction, GroupForming{ 
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
    public Person[] getParticipants() {
        return participants;
    }
    public String getNamedParticipants() {
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
    public void setParticipants(Person[] personArray) {
        participants = personArray;
    }

    @Override
    public Controller Sleep(Location location) {
        return Controller.SUCCESSFULLY;
    }
    @Override
    public Controller goTo(Location location) {
        return Controller.SUCCESSFULLY;
    }
    @Override
    public int getTimeGoing(Location location) {
        float[] arrayOfTimesOnDistance = new float[countOfParticipants];
        int position = 0;
        for (Person participant : participants) {
            arrayOfTimesOnDistance[position] = participant.getTimeGoing(location); 
            position++;
        } 
        float maxTime = 0;
        Arrays.sort(arrayOfTimesOnDistance);
        if (countOfParticipants != 0) {
            maxTime = arrayOfTimesOnDistance[countOfParticipants - 1];
        }
        currentCoordinate = location.getCoordinates();
        return (int) Math.ceil(maxTime);
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
    public Controller speakTo(Message messageObj, Person targetPerson) {
        return messageObj.getIsMessageTruly() ? Controller.SUCCESSFULLY : Controller.FAILED;
    }
    @Override
    public Controller speakTo(Message messageObj, PersonGroup tagetGroup) {
        return messageObj.getIsMessageTruly() ? Controller.SUCCESSFULLY : Controller.FAILED;
    }
    @Override
    public Controller speakTo(Message messageObj) {
        return messageObj.getIsMessageTruly() ? Controller.SUCCESSFULLY : Controller.FAILED;
    }

    @Override
    public Controller getInTheVehicle(Vehicle vehicle) {
        for (Person participant : participants) {
            if (participant.getInTheVehicle(vehicle) == Controller.FAILED) {
                return Controller.FAILED;
            }
        }
        return Controller.SUCCESSFULLY;
    }
    @Override
    public Controller getOutTheVehicle(Vehicle vehicle) {
        for (Person participant : participants) {
            if (participant.getOutTheVehicle(vehicle) == Controller.FAILED) {
                return Controller.FAILED;
            }
        }
        return Controller.SUCCESSFULLY;
    }
    
    @Override
    public Controller getInTheGroup(PersonGroup personGroupObj) {
        for (Person participant : participants) {
            if (participant.getInTheGroup(personGroupObj) == Controller.FAILED) {
                return Controller.FAILED;
            }
        }
        return Controller.SUCCESSFULLY;
    }

    @Override
    public Controller getOutTheGroup(PersonGroup personGroupObj) {
        for (Person participant : participants) {
            if (participant.getOutTheGroup(personGroupObj) == Controller.FAILED) {
                return Controller.FAILED;
            }
        }
        return Controller.SUCCESSFULLY;
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
