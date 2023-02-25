package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.interfaces.ValidatorInterface;

public class NameCityValidator implements ValidatorInterface<String>{
    @Override
    public boolean validate(String variable) {
        return (variable != null && variable.trim().length() != 0);
    }
    
}
