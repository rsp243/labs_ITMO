package src.classes;

import src.interfaces.MoveAction;
import src.interfaces.SeeAction;
import src.enums.Controller;
import src.enums.SatietyType;
import src.interfaces.eatAction;

public abstract class Creature implements MoveAction, SeeAction, eatAction{
    private String name;
    private Coordinate currentCoordinates;
    private int speed;
    private SatietyType satiety;

    public Creature(String name, Coordinate currentCoordinates, int speed, SatietyType aSatiety) {
        this.name = name;
        this.currentCoordinates = currentCoordinates;
        this.speed = speed;
        this.satiety = aSatiety;
    }

    @Override
    public Controller goTo(Location location) {
        return Controller.SUCCESSFULLY;
    }
    @Override
    public int getTimeGoing(Location location) throws NumberFormatException {
        float distance = (float) (Math.pow(Math.pow(location.getCoordinates().getRightPosition() - currentCoordinates.getRightPosition(), 2)
            + Math.pow(location.getCoordinates().getTopPosition() - currentCoordinates.getTopPosition(), 2)
            + Math.pow(location.getCoordinates().getHeightPosition() - currentCoordinates.getHeightPosition(), 2), 0.5));
        currentCoordinates = location.getCoordinates();
        return (int) Math.ceil(400 * distance / speed / 1000);
    }
    public String getName() {
        return name;   
    }
    public Coordinate getCurrentCoordinates() {
        return currentCoordinates;
    }
    public void setCurrentCoordinates(Coordinate currentCoordinates) {
        this.currentCoordinates = currentCoordinates;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public SatietyType getSatiety() {
        return satiety;
    }
    @Override
    public Controller see(Creature seeingCreature) {
        return Controller.SUCCESSFULLY;
    }

    @Override
    public Controller see(PersonGroup seeingGroup) {
        return Controller.SUCCESSFULLY;
    }

    @Override
    public Controller see(Location seeingLocation) {
        return Controller.SUCCESSFULLY;
    }

    @Override
    public Controller see(Vehicle seeingVehicle) {
        return Controller.SUCCESSFULLY;
    }
    
    @Override
    public Controller eat(Meal mealObj) {

        return Controller.SUCCESSFULLY;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((currentCoordinates == null) ? 0 : currentCoordinates.hashCode());
        result = prime * result + speed;
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
        Creature other = (Creature) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (currentCoordinates == null) {
            if (other.currentCoordinates != null)
                return false;
        } else if (!currentCoordinates.equals(other.currentCoordinates))
            return false;
        if (speed != other.speed)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Creature [name=" + name + ", currentCoordinates=" + currentCoordinates + ", speed=" + speed + "]";
    }
}
