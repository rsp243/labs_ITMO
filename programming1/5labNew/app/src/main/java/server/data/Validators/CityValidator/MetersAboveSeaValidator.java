package server.data.Validators.CityValidator;

import server.data.Validators.AbstractValidator;

public class MetersAboveSeaValidator extends AbstractValidator<Integer> {

    public MetersAboveSeaValidator() {
        super("City.metersAboveSeaLevel", "none");
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
