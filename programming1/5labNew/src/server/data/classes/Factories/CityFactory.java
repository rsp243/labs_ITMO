package server.data.classes.Factories;

import java.util.ArrayList;

import server.data.classes.City;
import server.data.classes.Coordinates;
import server.data.classes.Human;
import server.data.enums.Climate;
import server.fillers.Increment;

public class CityFactory {
    // It's nessesary to edit this class, because if we create a lot of objects City, their id-s would be the same = 1
    private Increment uniqueID = new Increment(1);

    public City createCity(ArrayList<String> args) {
        String[] coordinatesValues = {args.get(1), args.get(2)}; 
        String[] humanValues = {args.get(9), args.get(10), args.get(11)};

        String name = args.get(0);
        Coordinates coordinates = new CoordinatesFactory().createCoordinatesObj(coordinatesValues);
        int area = Integer.parseInt(args.get(3));
        Integer population = Integer.parseInt(args.get(4));
        Integer metersAboveSeaLevel = Integer.parseInt(args.get(5));
        Long telephoneCode = Long.parseLong(args.get(6));
        Long carCode = Long.parseLong(args.get(7));
        Climate climate = Climate.valueOf(args.get(8));
        Human governor = new HumanFactory().createHumanObj(humanValues);
        City newCity = new City(uniqueID, name, coordinates, area, population, metersAboveSeaLevel, telephoneCode,
                carCode, climate, governor);
        return newCity;
    }

    public Increment getUniqueID() {
        return uniqueID;
    }
}