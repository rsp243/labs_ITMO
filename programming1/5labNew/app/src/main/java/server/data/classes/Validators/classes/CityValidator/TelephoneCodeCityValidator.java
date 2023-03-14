package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.classes.Validators.interfaces.ValidatorInterface;

public class TelephoneCodeCityValidator extends AbstractValidator implements ValidatorInterface<Long> {
    public TelephoneCodeCityValidator() {
        super("City.telephoneCode");
    }

    @Override
    public boolean validate(Long variable) {
        return (variable > 0 && variable < 100000);
    }
}
