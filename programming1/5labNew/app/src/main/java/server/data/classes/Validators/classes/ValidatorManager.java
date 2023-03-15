package server.data.classes.Validators.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import server.data.classes.Validators.classes.CityValidator.AreaCityValidator;
import server.data.classes.Validators.classes.CityValidator.CarCodeCityValidator;
import server.data.classes.Validators.classes.CityValidator.CityValidator;
import server.data.classes.Validators.classes.CityValidator.NameCityValidator;
import server.data.classes.Validators.classes.CityValidator.PopulationCityValidator;
import server.data.classes.Validators.classes.CityValidator.TelephoneCodeCityValidator;
import server.data.classes.Validators.classes.CoordinateValidator.CoordinateXValidator;
import server.data.classes.Validators.classes.CoordinateValidator.CoordinateYValidator;
import server.data.classes.Validators.classes.CoordinateValidator.CoordinatesValidator;
import server.data.classes.Validators.classes.HumanValidator.AgeHumanValidator;
import server.data.classes.Validators.classes.HumanValidator.HeightHumanValidator;
import server.data.classes.Validators.classes.HumanValidator.HumanValidator;

public class ValidatorManager {
    // Holy shit
    private ArrayList<AbstractValidator> validatorList;
    private LinkedHashMap<String, String> fields;

    public ValidatorManager(LinkedHashMap<String, String> afields) {
        validatorList = new ArrayList<AbstractValidator>();
        fields = afields;
        validatorList.add(null);
        NameCityValidator nameCityValidator = new NameCityValidator();
        validatorList.add(nameCityValidator);
        CoordinateXValidator coordinateXValidator = new CoordinateXValidator();
        validatorList.add(coordinateXValidator);
        CoordinateYValidator coordinateYValidator = new CoordinateYValidator();
        validatorList.add(coordinateYValidator);
        validatorList.add(null);
        AreaCityValidator areaCityValidator = new AreaCityValidator();
        validatorList.add(areaCityValidator);
        PopulationCityValidator populationCityValidator = new PopulationCityValidator();
        validatorList.add(populationCityValidator);
        validatorList.add(null);
        TelephoneCodeCityValidator telephoneCodeCityValidator = new TelephoneCodeCityValidator();
        validatorList.add(telephoneCodeCityValidator);
        CarCodeCityValidator carCodeCityValidator = new CarCodeCityValidator();
        validatorList.add(carCodeCityValidator);
        validatorList.add(null);
        AgeHumanValidator ageHumanValidator = new AgeHumanValidator();
        validatorList.add(ageHumanValidator);
        HeightHumanValidator heightHumanValidator = new HeightHumanValidator();
        validatorList.add(heightHumanValidator);
        validatorList.add(null);
        //14
        CityValidator cityValidator = new CityValidator();
        validatorList.add(cityValidator);
        //15
        CoordinatesValidator coordinatesValidator = new CoordinatesValidator();
        validatorList.add(coordinatesValidator);
        //16
        HumanValidator humanValidator = new HumanValidator();
        validatorList.add(humanValidator);
        System.out.println(validatorList.toString());
    }

    public AbstractValidator getValidator(String fieldName) {        
        // Do validation & deparametrization.

        int iter = 0;
        int position = 0;
        for (String field : fields.keySet()) {
            if (fieldName == field) {
                position = iter;
            } 
            iter++;
        }
        if (position <= 12) {
            return validatorList.get(position);
        }
        if (fieldName == "City") {
            return validatorList.get(14);
        } else {
            if (fieldName == "Coordinates") {
                return validatorList.get(15);
            } else {
                if (fieldName == "Human") {
                    return validatorList.get(16);
                } 
            }
        }
        return null;
    }

    public ArrayList<AbstractValidator> getValidatorList() {
        return validatorList;
    }

    public LinkedHashMap<String, String> getFields() {
        return fields;
    }
}
