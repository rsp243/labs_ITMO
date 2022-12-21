package src.classes;

import src.interfaces.MoveAction;
import src.interfaces.SeeAction;

public abstract class Creature implements MoveAction, SeeAction{
    private String name;
    private Coordinate currentCoordinates;
    private int speed;

    public Creature(String name, Coordinate currentCoordinates, int speed) {
        this.name = name;
        this.currentCoordinates = currentCoordinates;
        this.speed = speed;
    }

    @Override
    public float goTo(Location location) {
        float distance = (float) (Math.pow(Math.pow(location.getCoordinates().getRightPosition() - currentCoordinates.getRightPosition(), 2)
            + Math.pow(location.getCoordinates().getTopPosition() - currentCoordinates.getTopPosition(), 2), 0.555));
        currentCoordinates = location.getCoordinates();
        return 400 * distance / speed / 1000;
    }
    @Override
    public String toString() {
        return "Creature [name=" + name + ", currentCoordinates=" + currentCoordinates + ", speed=" + speed + "]";
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

    @Override
    public Creature See(Creature seeingCreature) {
        return seeingCreature;
    }

    @Override
    public PersonGroup See(PersonGroup seeingGroup) {
        return seeingGroup;
    }

    @Override
    public Location See(Location seeingLocation) {
        return seeingLocation;
    }

    @Override
    public Vehicle See(Vehicle seeingVehicle) {
        return seeingVehicle;
    }
    
}
