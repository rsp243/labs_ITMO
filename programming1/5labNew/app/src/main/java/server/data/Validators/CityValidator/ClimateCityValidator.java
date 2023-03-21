package server.data.Validators.CityValidator;

import server.data.Validators.AbstractValidator;
import server.data.enums.Climate;

public class ClimateCityValidator extends AbstractValidator<Enum> {

    public ClimateCityValidator() {
        super("City.Climate", "not null");
    }

    @Override
    public Enum caster(String str) {
        return Climate.valueOf(str);
    }

    @Override
    public boolean validate(Enum variable) {
        return variable != null;
    }  
}
