package src.server.data.classes.Validators.classes.CityValidator;

import src.server.data.classes.Validators.interfaces.ValidatorInterface;

public class PopulationCityValidator implements ValidatorInterface<Integer>{
    @Override
    public boolean validate(Integer variable) {
        return (variable != null && variable > 0);
    }
}
