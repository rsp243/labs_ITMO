package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.classes.Validators.interfaces.ValidatorInterface;

public class CarCodeCityValidator extends AbstractValidator implements ValidatorInterface<Long> {
    public CarCodeCityValidator() {
        super("City.carCode");
    }

    @Override
    public boolean validate(Long variable) {
        return (variable != null && variable > 0 && variable < 1000);
    }
}
