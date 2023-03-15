package server.data.Validators.CityValidator;

import java.util.ArrayList;

import server.data.Validators.AbstractValidator;
import server.data.Validators.CoordinateValidator.CoordinatesValidator;
import server.data.Validators.HumanValidator.HumanValidator;
import server.data.enums.Climate;

public class CityValidator extends AbstractValidator<ArrayList<String>> {
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
