package src.data.classes.Validators.classes.CityValidator;

import java.util.ArrayList;

import src.data.classes.Validators.classes.CoordinateValidator.CoordinatesValidator;
import src.data.classes.Validators.classes.HumanValidator.HumanValidator;
import src.data.classes.Validators.interfaces.ValidatorInterface;
import src.data.enums.Climate;

public class CityValidator implements ValidatorInterface<ArrayList<String>>{
    @Override
    public boolean validate(ArrayList<String> args) {
        // Needed to be added Coordinates, Climate, Human Validators
        if (new NameCityValidator().validate(args.get(0)) &&
        new CoordinatesValidator().validate(args.get(1).split(" ")) && 
        new AreaCityValidator().validate(Integer.parseInt(args.get(2))) && 
        new PopulationCityValidator().validate(Integer.parseInt(args.get(3))) && 
        new TelephoneCodeCityValidator().validate(Long.parseLong(args.get(5))) &&
        new CarCodeCityValidator().validate(Long.parseLong(args.get(6))) && 
        Climate.valueOf(args.get(7)) != null && 
        new HumanValidator().validate(args.get(8).split(" "))) {
            return true;
        }
        return false;
    }
}
