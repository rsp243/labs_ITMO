package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.classes.Validators.interfaces.ValidatorInterface;

public class NameCityValidator extends AbstractValidator implements ValidatorInterface<String>{
    public NameCityValidator() {
        super("City.name");
    }
    
    @Override
    public boolean validate(String variable) {
        return (variable != null && variable.trim().length() != 0);
    }
}
