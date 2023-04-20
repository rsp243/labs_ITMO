package server.data.Validators.CityValidator;

import server.data.Validators.AbstractValidator;

public class AreaCityValidator extends AbstractValidator<Integer> {
    public AreaCityValidator() {
        super("City.area", "not null, more than 0");
    }

    @Override
    public boolean validate(Integer variable) {
        return (variable != null && variable > 0);
    }

    @Override
    public Integer caster(String str) {
        return Integer.parseInt(str);
    }
}
