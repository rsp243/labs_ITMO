package src.data.classes.Validators.classes.CityValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class CarCodeCityValidator implements ValidatorInterface<Integer> {
    @Override
    public boolean validate(Integer variable) {
        return (variable > 0 && variable < 1000);
    }
    
}
