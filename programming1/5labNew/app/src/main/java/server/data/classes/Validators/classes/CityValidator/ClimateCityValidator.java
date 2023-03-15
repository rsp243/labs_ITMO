package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.enums.Climate;

public class ClimateCityValidator extends AbstractValidator<Enum> {

    public ClimateCityValidator() {
        super("City.Climate");
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
