package server.data.Factories;

import java.util.ArrayList;

import server.data.City;
import server.data.Coordinates;
import server.data.Human;
import server.data.enums.Climate;

/**
 * CityFactory Class
 * create City object
 */

public class CityFactory {
    /**
     * Create City object
     * @param uniqueID
     * @param args arguments to City's fields
     * @return new City object from arguments
     */
    public City createCity(Long uniqueID, ArrayList<String> args) {
        String[] coordinatesValues = {args.get(1), args.get(2)}; 
        String[] humanValues = {args.get(9), args.get(10), args.get(11)};

        String name = args.get(0);
        Coordinates coordinates = new CoordinatesFactory().createCoordinatesObj(coordinatesValues);
        int area = Integer.parseInt(args.get(3));
        Integer population = Integer.parseInt(args.get(4));
        Integer metersAboveSeaLevel = null;
        if (!args.get(5).equals("null") && !args.get(5).equals("")) {
            metersAboveSeaLevel = Integer.parseInt(args.get(5));
        }
        Long telephoneCode = Long.parseLong(args.get(6));
        Long carCode = Long.parseLong(args.get(7));
        Climate climate = Climate.valueOf(args.get(8));
        Human governor = new HumanFactory().createHumanObj(humanValues);
        City newCity = new City(uniqueID, name, coordinates, area, population, metersAboveSeaLevel, telephoneCode,
                carCode, climate, governor);
        return newCity;
    }
}
