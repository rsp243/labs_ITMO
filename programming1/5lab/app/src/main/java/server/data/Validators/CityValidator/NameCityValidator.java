package server.data.Validators.CityValidator;

import server.data.Validators.AbstractValidator;

public class NameCityValidator extends AbstractValidator<String> {
    public NameCityValidator() {
        super("City.name", "not null, lentgth of String doesn't equal 0");
    }
    
    @Override
    public boolean validate(String variable) {
        return (variable != null && variable.trim().length() != 0);
    }
}
