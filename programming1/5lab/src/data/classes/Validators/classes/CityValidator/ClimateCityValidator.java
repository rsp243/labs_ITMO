package src.data.classes.Validators.classes.CityValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;
import src.data.enums.Climate;

public class ClimateCityValidator implements ValidatorInterface<Climate> {
    @Override
    public boolean validate(Climate variable) {
        return variable != null;
    }
    
}
