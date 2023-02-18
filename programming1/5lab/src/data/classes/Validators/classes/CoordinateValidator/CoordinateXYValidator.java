package src.data.classes.Validators.classes.CoordinateValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class CoordinateXYValidator implements ValidatorInterface<Double> {
    @Override
    public boolean validate(Double variable) {
        return (variable != null && variable > -212);
    }
}
