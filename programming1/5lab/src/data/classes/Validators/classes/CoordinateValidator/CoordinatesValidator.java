package src.data.classes.Validators.classes.CoordinateValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class CoordinatesValidator implements ValidatorInterface<String[]> {
    @Override
    public boolean validate(String[] args) {
        if (new CoordinateXYValidator().validate(Long.parseLong(args[0])) &&
        new CoordinateXYValidator().validate(Long.parseLong(args[1]))) {
            return true;
        }
        return false;
    }
    
}
