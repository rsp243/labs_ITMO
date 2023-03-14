package server.data.classes.Validators.classes.CoordinateValidator;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.classes.Validators.interfaces.ValidatorInterface;

public class CoordinateYValidator extends AbstractValidator implements ValidatorInterface<Long>{
    public CoordinateYValidator() {
        super("City.Coordinates.X");
    }

    @Override
    public boolean validate(Long variable) {
        return (variable != null && variable > -212);
    }
}
