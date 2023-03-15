package server.data.classes.Validators.classes.CoordinateValidator;

import server.data.classes.Validators.classes.AbstractValidator;

public class CoordinateXValidator extends AbstractValidator<Long> {    
    public CoordinateXValidator() {
        super("City.Coordinates.X");
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
