package src.data.classes.Validators.classes.CityValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class CarCodeCityValidator implements ValidatorInterface<Long> {
    @Override
    public boolean validate(Long variable) {
        return (variable > 0 && variable < 1000);
    }
    
}
