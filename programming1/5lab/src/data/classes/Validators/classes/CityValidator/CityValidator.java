package src.data.classes.Validators.classes.CityValidator;

import java.util.ArrayList;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class CityValidator implements ValidatorInterface<ArrayList<String>>{
    @Override
    public boolean validate(ArrayList<String> args) {
        // Needed to be added Coordinates, Climate, Human Validators
        if (new NameCityValidator().validate(args.get(0)) &&
        new AreaCityValidator().validate(Integer.parseInt(args.get(2))) && 
        new PopulationCityValidator().validate(Integer.parseInt(args.get(4))) && 
        new TelephoneCodeCityValidator().validate(Long.parseLong(args.get(5))) &&
        new CarCodeCityValidator().validate(Long.parseLong(args.get(6)))) {
            return true;
        }
        return false;
    }
}
