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
    private HashMap<String, AbstractValidator<?>> validatorHashMap = new HashMap<>();

    public ValidatorManager(LinkedHashMap<String, String> fields) {
        validatorHashMap = new HashMap<>();
        CityValidator cityValidator = new CityValidator();
        validatorHashMap.put(cityValidator.getName(), cityValidator);
        AreaCityValidator areaCityValidator = new AreaCityValidator();
        validatorHashMap.put(areaCityValidator.getName(), areaCityValidator);
        CarCodeCityValidator carCodeCityValidator = new CarCodeCityValidator();
        validatorHashMap.put(carCodeCityValidator.getName(), carCodeCityValidator);
        NameCityValidator nameCityValidator = new NameCityValidator();
        validatorHashMap.put(nameCityValidator.getName(), nameCityValidator);
        PopulationCityValidator populationCityValidator = new PopulationCityValidator();
        validatorHashMap.put(populationCityValidator.getName(), populationCityValidator);
        TelephoneCodeCityValidator telephoneCodeCityValidator = new TelephoneCodeCityValidator();
        validatorHashMap.put(telephoneCodeCityValidator.getName(), telephoneCodeCityValidator);
        CoordinatesValidator coordinatesValidator = new CoordinatesValidator();
        validatorHashMap.put(coordinatesValidator.getName(), coordinatesValidator);
        CoordinateXValidator coordinateXValidator = new CoordinateXValidator();
        validatorHashMap.put(coordinateXValidator.getName(), coordinateXValidator);
        CoordinateYValidator coordinateYValidator = new CoordinateYValidator();
        validatorHashMap.put(coordinateYValidator.getName(), coordinateYValidator);
        HumanValidator humanValidator = new HumanValidator();
        validatorHashMap.put(humanValidator.getName(), humanValidator);
        AgeHumanValidator ageHumanValidator = new AgeHumanValidator();
        validatorHashMap.put(ageHumanValidator.getName(), ageHumanValidator);
        HeightHumanValidator heightHumanValidator = new HeightHumanValidator();
        validatorHashMap.put(heightHumanValidator.getName(), heightHumanValidator);
    }

    public boolean validate(String fieldName, ArrayList<String> arguments) {
        if (validatorHashMap.keySet().contains(fieldName)) {
            // Do validation & deparametrization.
            return validatorHashMap.get(fieldName).validate(fieldName);
        }
        return true;
    }
}
