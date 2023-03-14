package server.data.classes.Validators.classes.CityValidator;

import java.util.ArrayList;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.classes.Validators.classes.CoordinateValidator.CoordinatesValidator;
import server.data.classes.Validators.classes.HumanValidator.HumanValidator;
import server.data.classes.Validators.interfaces.ValidatorInterface;
import server.data.enums.Climate;

public class CityValidator extends AbstractValidator implements ValidatorInterface<ArrayList<String>>{
    public CityValidator() {
        super("City");
    }

    @Override
    public boolean validate(ArrayList<String> args) {
        String[] coordinatesToValidate = {args.get(1), args.get(2)}; 
        String[] humanToValidate = {args.get(9), args.get(10), args.get(11)};

        if (new NameCityValidator().validate(args.get(0)) &&
        new CoordinatesValidator().validate(coordinatesToValidate) && 
        new AreaCityValidator().validate(Integer.parseInt(args.get(3))) && 
        new PopulationCityValidator().validate(Integer.parseInt(args.get(4))) && 
        new TelephoneCodeCityValidator().validate(Long.parseLong(args.get(5))) &&
        new CarCodeCityValidator().validate(Long.parseLong(args.get(7))) && 
        Climate.valueOf(args.get(8)) != null && 
        new HumanValidator().validate(humanToValidate)) {
            return true;
        }
        return false;
    }
}
