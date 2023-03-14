package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.classes.Validators.interfaces.ValidatorInterface;

public class AreaCityValidator extends AbstractValidator implements ValidatorInterface<Integer> {
    public AreaCityValidator() {
        super("City.area");
    }

    @Override
    public boolean validate(Integer variable) {
        return (variable != null && variable > 0);
    }
}
