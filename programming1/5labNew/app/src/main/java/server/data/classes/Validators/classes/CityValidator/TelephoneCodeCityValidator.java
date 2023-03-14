package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;

public class TelephoneCodeCityValidator extends AbstractValidator<Long> {
    public TelephoneCodeCityValidator() {
        super("City.telephoneCode");
    }

    @Override
    public boolean validate(Long variable) {
        return (variable > 0 && variable < 100000);
    }
}
