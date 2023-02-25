package src.server.data.classes.Factories;

import src.server.data.classes.Coordinates;

public class CoordinatesFactory {
    public Coordinates createCoordinatesObj(String[] args) {
        long x = Long.parseLong(args[0]);
        long y = Long.parseLong(args[1]);
        Coordinates newCoordinates = new Coordinates(x, y);
        return newCoordinates;
    }
}
