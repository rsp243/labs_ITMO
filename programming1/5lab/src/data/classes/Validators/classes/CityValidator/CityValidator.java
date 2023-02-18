package src.data.classes.Validators.classes.CityValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class CityValidator implements ValidatorInterface<String[]>{
    @Override
    public boolean validate(String[] args) {
        // Needed to be added Coordinates, Climate, Human Validators
        if (new NameCityValidator().validate(args[0]) &&
        new AreaCityValidator().validate(Long.parseLong(args[2])) && 
        new PopulationCityValidator().validate(Integer.parseInt(args[4])) && 
        new TelephoneCodeCityValidator().validate(Integer.parseInt(args[5])) &&
        new CarCodeCityValidator().validate(Integer.parseInt(args[6]))) {
            return true;
        }
        return false;
    }
}
