package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.interfaces.ValidatorInterface;

public class TelephoneCodeCityValidator implements ValidatorInterface<Long> {
    @Override
    public boolean validate(Long variable) {
        return (variable > 0 && variable < 100000);
    }   
}
