package server.data.Validators.CityValidator;

import server.data.Validators.AbstractValidator;

public class CarCodeCityValidator extends AbstractValidator<Long> {
    public CarCodeCityValidator() {
        super("City.carCode", "not null, more than 0, less than 1000");
    }

    @Override
    public boolean validate(Long variable) {
        return (variable != null && variable > 0 && variable < 1000);
    }
    
    
    @Override
    public Long caster(String str) {
        return Long.parseLong(str);
    }
}
