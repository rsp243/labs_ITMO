package src.classes;

import java.util.Arrays;

import src.enums.*;
import src.interfaces.*;
import java.lang.Math;
import java.lang.Object;

public class Person extends Creature implements ContollingClothesAction,
                            ControllingEmotionsAction,
                            SpeakingAction,  
                            AbleToSleep, 
                            DetainAction, 
                            VehicleAction,
                            WorkAction,
                            GroupForming{
    private String secondName;
    private Clothes[] arrayOfClothes;
    private EmotionType[] arrayOfEmotions;
    private Freedom freedom;
    private int money;
    private Profession profession;

    public Person(String name) {
        this(name, "", new Clothes[5],
        new EmotionType[5], Freedom.FREE, new Coordinate(0, 0, 0), 5, 0, SatietyType.WELL_FED, Profession.UNEMPLOYED);
    }
    
    public Person(String aName, String aSecondName, Clothes[] anArrayOfClothes, EmotionType[] anArrayOfEmotions,
            Freedom aFreedom, Coordinate aCurrentCoordinates, int aSpeed, int aMoney,SatietyType aSatiety, Profession aProfession) {
        super(aName, aCurrentCoordinates, aSpeed, aSatiety);
        this.secondName = aSecondName;
        this.arrayOfClothes = anArrayOfClothes;
        this.arrayOfEmotions = anArrayOfEmotions;
        this.freedom = aFreedom;
        this.money = aMoney;
        this.profession = aProfession;
    }

    public String getSecondName() {
        return secondName;
    }
    public String getNamedArrayOfClothes() {
        String[] arrayOfNamedClothes = new String[arrayOfClothes.length];
        int position = 0;
        for (Clothes iter : arrayOfClothes) {
            if (iter != null) {
                arrayOfNamedClothes[position] = iter.getName();
            } else {
                arrayOfNamedClothes[position] = "null";
            }
            position += 1;
        }
        return Arrays.toString(arrayOfNamedClothes);
    }
    public Clothes[] getArrayOfClothes() {
        return arrayOfClothes;
    }
    public void setArrayOfClothes(Clothes[] arrayOfClothes) {
        this.arrayOfClothes = arrayOfClothes;
    }
    public String getNamedArrayOfEmotions() {   
        String[] arrayOfNamedEmotions = new String[arrayOfEmotions.length];
        int position = 0;
        for (EmotionType iter : arrayOfEmotions) {
            if (iter != null) {
                arrayOfNamedEmotions[position] = iter.getName();
            } else {
                arrayOfNamedEmotions[position] = "null";
            }
            position += 1;
        }
        return Arrays.toString(arrayOfNamedEmotions);
    }
    public EmotionType[] getArrayOfEmotions() {
        return arrayOfEmotions;
    }
    public void setArrayOfEmotions(EmotionType[] arrayOfEmotions) {
        this.arrayOfEmotions = arrayOfEmotions;
    }
    public Freedom getFreedom() {
        return freedom;
    }
    public void setFreedom(Freedom freedom) {
        this.freedom = freedom;
    }
    public int getMoney() {
        return money;
    }
    public Controller setMoney(int money) {
        this.money = money;
        return Controller.SUCCESSFULLY;
    }
    public Controller setProfession(Profession profession) {
        this.profession = profession;
        return Controller.SUCCESSFULLY;
    }

    public Profession getProfession() {
        return profession;
    }

    @Override
    public Controller ControlFreedom(Person targetPerson, Freedom freedom) {
        if (Math.random() < 0.5) {
            return Controller.FAILED;
        } else {
            targetPerson.setFreedom(freedom);
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller ControlFreedom(PersonGroup targetGroup, Freedom freedom) {
        return Controller.FAILED;
    }
    @Override
    public Controller Sleep(Location location) {
        return Controller.SUCCESSFULLY;
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
    public Controller addEmotion(Person person, EmotionType emotion) {
        int position = 0;
        for (EmotionType iter : person.arrayOfEmotions) {
            if (iter != null) {
                position += 1;
            }
        }
        if (position >= person.arrayOfEmotions.length) {
            return Controller.FAILED;
        } else {
            person.arrayOfEmotions[position] = emotion;
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller removeEmotion(Person person, EmotionType emotion) {
        int position = 0;
        for (EmotionType iter : person.arrayOfEmotions) {
            if (iter != emotion) {
                position += 1;
            } else {
                person.arrayOfEmotions[position] = null;
            }
        }
        if (position == person.arrayOfEmotions.length) {
            return Controller.FAILED;
        } else {
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller addEmotion(EmotionType emotion) {
        int position = 0;
        for (EmotionType iter : arrayOfEmotions) {
            if (iter != null) {
                position += 1;
            }
        }
        if (position >= arrayOfEmotions.length) {
            return Controller.FAILED;
        } else {
            arrayOfEmotions[position] = emotion;
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller removeEmotion(EmotionType emotion) {
        int position = 0;
        int countDeletedEmotions = 0;
            for (EmotionType iter : arrayOfEmotions) {
            if (iter == emotion) {
                arrayOfEmotions[position] = null;
                countDeletedEmotions++;
            }
            position++;
        }
        if (countDeletedEmotions == 0) {
            return Controller.FAILED;
        } else {
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller addEmotion(PersonGroup personGroup, EmotionType emotion) {
        int countAddedEmotions = 0;
        for (Person person : personGroup.getParticipants()) {
            if (person.addEmotion(emotion) == Controller.SUCCESSFULLY) {
                countAddedEmotions += 1;
            }
        }    
        if (countAddedEmotions != personGroup.getParticipants().length) {
            return Controller.FAILED;
        } else {
            return Controller.SUCCESSFULLY;
        }
    }

    @Override
    public Controller removeEmotion(PersonGroup personGroup, EmotionType emotion) {
        int countDeletedEmotions = 0;
        for (Person person : personGroup.getParticipants()) {
            if (person.removeEmotion(emotion) == Controller.SUCCESSFULLY) {
                countDeletedEmotions += 1;
            }
        }    
        if (countDeletedEmotions != personGroup.getParticipants().length) {
            return Controller.FAILED;
        } else {
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller putOnClothes(Clothes clothes) {
        int position = 0;
        for (Clothes iter : arrayOfClothes) {
            if (iter != null) {
                position += 1;
            }
        }
        if (position >= arrayOfClothes.length) {
            return Controller.FAILED;
        } else {
            arrayOfClothes[position] = clothes;
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller takeOffClothes(Clothes clothes) {
        int position = 0;
        int countDeletedClothes = 0;
        for (Clothes iter : arrayOfClothes) {
            if (iter == clothes) {
                arrayOfClothes[position] = null;
                countDeletedClothes++;
            }
            position++;
        }
        if (countDeletedClothes == 0) {
            return Controller.FAILED;
        } else {
            return Controller.SUCCESSFULLY;
        }
    }
    @Override
    public Controller getInTheVehicle(Vehicle vehicle) {
        Person[] passangers = vehicle.getPassengers();
        int countPassangers = 0;
        for (Person passanger : passangers) {
            if (passanger != null) {
                countPassangers++;
            }
        }
        if (countPassangers <= vehicle.getCountOfSeats()) {
            int position = 0;
            for (Person passanger : passangers) {
                if (passanger != null) {
                    position++;
                }
            }
            passangers[position] = this;
            vehicle.setPassengers(passangers);
            return Controller.SUCCESSFULLY;
        } else {
            return Controller.FAILED;
        }
    }
    @Override
    public Controller getOutTheVehicle(Vehicle vehicle) {
        int position = 0;
        Person[] passangersArray = vehicle.getPassengers();
        int countOfDeletedPassangers = 0;
        for (Person iter : passangersArray) {
            if (iter == this) {
                passangersArray[position] = null;
                countOfDeletedPassangers++;
            }
            position++;
        }
        if (countOfDeletedPassangers == 0) return Controller.FAILED;
        return Controller.SUCCESSFULLY;
    }
    @Override
    public Controller work(int timeOfWorking) {
        money = (int) Math.floor(timeOfWorking / 100 * profession.getSalary());
        return Controller.SUCCESSFULLY;
    }
    @Override
    public int getEarnedMoney(int timeOfWorking) {
        return (int) Math.floor(timeOfWorking / 100 * profession.getSalary());
    }
    @Override
    public Controller getInTheGroup(PersonGroup personGroupObj) {
        Person[] participants = personGroupObj.getParticipants();
        int countPassangers = 0;
        for (Person passanger : participants) {
            if (passanger != null) {
                countPassangers++;
            }
        }
        if (countPassangers <= personGroupObj.getCountOfParticipants()) {
            int position = 0;
            for (Person passanger : participants) {
                if (passanger != null) {
                    position++;
                }
            }
            participants[position] = this;
            personGroupObj.setParticipants(participants);
            return Controller.SUCCESSFULLY;
        } else {
            return Controller.FAILED;
        }
    }
    @Override
    public Controller getOutTheGroup(PersonGroup personGroupObj) {
        int position = 0;
        Person[] participants = personGroupObj.getParticipants();
        int countOfDeletedPassangers = 0;
        for (Person iter : participants) {
            if (iter == this) {
                participants[position] = null;
                countOfDeletedPassangers++;
            }
            position++;
        }
        if (countOfDeletedPassangers == 0) return Controller.FAILED;
        return Controller.SUCCESSFULLY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
        result = prime * result + Arrays.hashCode(arrayOfClothes);
        result = prime * result + Arrays.hashCode(arrayOfEmotions);
        result = prime * result + ((freedom == null) ? 0 : freedom.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (secondName == null) {
            if (other.secondName != null)
                return false;
        } else if (!secondName.equals(other.secondName))
            return false;
        if (!Arrays.equals(arrayOfClothes, other.arrayOfClothes))
            return false;
        if (!Arrays.equals(arrayOfEmotions, other.arrayOfEmotions))
            return false;
        if (freedom != other.freedom)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Person [secondName=" + secondName + ", arrayOfClothes=" + Arrays.toString(arrayOfClothes)
                + ", arrayOfEmotions=" + Arrays.toString(arrayOfEmotions) + ", freedom=" + freedom + "]";
    }
}