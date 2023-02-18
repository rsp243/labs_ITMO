package src.data.classes.Validators.classes.CityValidator;

import src.data.classes.Coordinates;
import src.data.classes.Validators.interfaces.ValidatorInterface;

public class CoordinatesCityValidator implements ValidatorInterface<Coordinates>{
    @Override
    public boolean validate(Coordinates variable) {
        return variable != null;
    }
}
