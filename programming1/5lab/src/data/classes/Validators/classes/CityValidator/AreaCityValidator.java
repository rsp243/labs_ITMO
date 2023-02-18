package src.data.classes.Validators.classes.CityValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class AreaCityValidator implements ValidatorInterface<Long> {
    @Override
    public boolean validate(Long variable) {
        return (variable != null && variable > 0);
    }
    
}
