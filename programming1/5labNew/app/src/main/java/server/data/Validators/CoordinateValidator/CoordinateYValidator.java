package server.data.Validators.CoordinateValidator;

import server.data.Validators.AbstractValidator;

public class CoordinateYValidator extends AbstractValidator<Long>{
    public CoordinateYValidator() {
        super("City.Coordinates.X", "not null, more than -212");
    }

    @Override
    public boolean validate(Long variable) {
        return (variable != null && variable > -212);
    }

    @Override
    public Long caster(String str) {
        return Long.parseLong(str);
    }
}
