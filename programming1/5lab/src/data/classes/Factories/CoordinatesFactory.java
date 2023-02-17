package src.data.classes.Factories;

import src.data.classes.Coordinates;

public class CoordinatesFactory {
    public CoordinatesFactory() {
    }
    
    public Coordinates createCoordinatesObj(String[] args) {
        Double x = Double.parseDouble(args[0]);
        Double y = Double.parseDouble(args[1]);
        Coordinates newCoordinates = new Coordinates(x, y);
        return newCoordinates;
    }
}
