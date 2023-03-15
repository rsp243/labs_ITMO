package server.data.Validators.CityValidator;

import server.data.Validators.AbstractValidator;

public class PopulationCityValidator extends AbstractValidator<Integer> {
    public PopulationCityValidator() {
        super("City.population");
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
