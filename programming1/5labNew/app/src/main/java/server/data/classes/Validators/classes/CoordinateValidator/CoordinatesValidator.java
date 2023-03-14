package server.data.classes.Validators.classes.CoordinateValidator;

import server.data.classes.Validators.classes.AbstractValidator;

public class CoordinatesValidator extends AbstractValidator<String[]> {
    public CoordinatesValidator() {
        super("City.Coordinates");
    }

    @Override
    public boolean validate(String[] args) {
        if (new CoordinateXValidator().validate(Long.parseLong(args[0])) &&
        new CoordinateYValidator().validate(Long.parseLong(args[1]))) {
            return true;
        }
        return false;
    }
}
