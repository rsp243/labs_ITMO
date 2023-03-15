package server.data.classes.Validators.classes.CityValidator;

import server.data.classes.Validators.classes.AbstractValidator;

public class MetersAboveSeaValidator extends AbstractValidator<Integer> {

    public MetersAboveSeaValidator() {
        super("City.metersAboveSeaLevel");
    }

    @Override
    public Integer caster(String str) {
        if (!str.equals("")) {
            return Integer.parseInt(str);
        }
        return null;
    }

    @Override
    public boolean validate(Integer variable) {
        return super.validate(variable);
    }
}
