package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;

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
