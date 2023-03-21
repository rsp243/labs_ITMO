package server.data.Validators;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import server.data.Validators.CityValidator.AreaCityValidator;
import server.data.Validators.CityValidator.CarCodeCityValidator;
import server.data.Validators.CityValidator.CityValidator;
import server.data.Validators.CityValidator.ClimateCityValidator;
import server.data.Validators.CityValidator.MetersAboveSeaValidator;
import server.data.Validators.CityValidator.NameCityValidator;
import server.data.Validators.CityValidator.PopulationCityValidator;
import server.data.Validators.CityValidator.TelephoneCodeCityValidator;
import server.data.Validators.CoordinateValidator.CoordinateXValidator;
import server.data.Validators.CoordinateValidator.CoordinateYValidator;
import server.data.Validators.CoordinateValidator.CoordinatesValidator;
import server.data.Validators.HumanValidator.AgeHumanValidator;
import server.data.Validators.HumanValidator.BirthdayHumanValidator;
import server.data.Validators.HumanValidator.HeightHumanValidator;
import server.data.Validators.HumanValidator.HumanValidator;

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
        MetersAboveSeaValidator metersAboveSeaLevel = new MetersAboveSeaValidator();
        validatorList.add(metersAboveSeaLevel);
        TelephoneCodeCityValidator telephoneCodeCityValidator = new TelephoneCodeCityValidator();
        validatorList.add(telephoneCodeCityValidator);
        CarCodeCityValidator carCodeCityValidator = new CarCodeCityValidator();
        validatorList.add(carCodeCityValidator);
        ClimateCityValidator climateCityValidator = new ClimateCityValidator();
        validatorList.add(climateCityValidator);
        AgeHumanValidator ageHumanValidator = new AgeHumanValidator();
        validatorList.add(ageHumanValidator);
        HeightHumanValidator heightHumanValidator = new HeightHumanValidator();
        validatorList.add(heightHumanValidator);
        BirthdayHumanValidator birthdayHumanValidator = new BirthdayHumanValidator();
        validatorList.add(birthdayHumanValidator);
    }

    public AbstractValidator getValidator(String fieldName) {        
        int iter = 0;
        int position = 0;
        for (String field : fields.keySet()) {
            if (fieldName == field) {
                position = iter;
            } 
            iter++;
        }
        if (position <= fields.size() - 1) {
            return validatorList.get(position);
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
