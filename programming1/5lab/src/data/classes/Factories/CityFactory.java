package src.data.classes.Factories;

import java.util.ArrayList;

import src.data.classes.City;
import src.data.classes.Coordinates;
import src.data.classes.Human;
import src.data.enums.Climate;
import src.fillers.Increment;

public class CityFactory {
    private Increment uniqueID = new Increment(1);

    public CityFactory() {
    }
    
    public City createCity(ArrayList<String> args) {
        String name = args.get(0);
        Coordinates coordinates = new CoordinatesFactory().createCoordinatesObj(args.get(1).split(" "));
        Long area = Long.parseLong(args.get(2));
        Integer population = Integer.parseInt(args.get(3));
        Integer metersAboveSeaLevel = Integer.parseInt(args.get(4));
        Integer telephoneCode = Integer.parseInt(args.get(5));
        Integer carCode = Integer.parseInt(args.get(6));
        Climate climate = Climate.valueOf(args.get(7));
        Human governor = new HumanFactory().createHumanObj(args.get(8).split(" "));
        City newCity = new City(uniqueID, name, coordinates, area, population, metersAboveSeaLevel, telephoneCode, carCode, climate, governor);
        return newCity; 
    }
}
