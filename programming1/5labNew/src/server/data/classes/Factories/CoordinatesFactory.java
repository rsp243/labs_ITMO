package server.data.classes.Factories;

import server.data.classes.Coordinates;

/**
 * CoordinatesFactory Class
 * create Coordinates object
 */

public class CoordinatesFactory {
    /**
     * Create Coordinates object
     * @param args String[] [x, y]
     * @return new Coordinates object from arguments
     */
    public Coordinates createCoordinatesObj(String[] args) {
        long x = Long.parseLong(args[0]);
        long y = Long.parseLong(args[1]);
        Coordinates newCoordinates = new Coordinates(x, y);
        return newCoordinates;
    }
}
