package server.data.Validators.CityValidator;

import server.data.Validators.AbstractValidator;

public class TelephoneCodeCityValidator extends AbstractValidator<Long> {
    public TelephoneCodeCityValidator() {
        super("City.telephoneCode");
    }

    @Override
    public boolean validate(Long variable) {
        return (variable > 0 && variable < 100000);
    }

    @Override
    public Long caster(String str) {
        return Long.parseLong(str);
    }
}
