package src.data.classes.Validators.classes.CityValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class PopulationCityValidator implements ValidatorInterface<Integer>{
    @Override
    public boolean validate(Integer variable) {
        return (variable != null && variable > 0);
    }
}
