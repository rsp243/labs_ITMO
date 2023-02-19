package src.data.classes.Validators.classes.CoordinateValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class CoordinateXYValidator implements ValidatorInterface<Long> {
    @Override
    public boolean validate(Long variable) {
        return (variable != null && variable > -212);
    }
}
