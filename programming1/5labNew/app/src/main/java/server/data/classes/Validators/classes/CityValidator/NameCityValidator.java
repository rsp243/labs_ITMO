package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;

public class NameCityValidator extends AbstractValidator<String> {
    public NameCityValidator() {
        super("City.name");
    }
    
    @Override
    public boolean validate(String variable) {
        return (variable != null && variable.trim().length() != 0);
    }
}
