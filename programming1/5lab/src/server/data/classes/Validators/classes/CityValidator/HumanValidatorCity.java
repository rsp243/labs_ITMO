package src.server.data.classes.Validators.classes.CityValidator;

import src.server.data.classes.Human;
import src.server.data.classes.Validators.interfaces.ValidatorInterface;

public class HumanValidatorCity implements ValidatorInterface<Human>{
    @Override
    public boolean validate(Human variable) {
        return variable != null;
    }
}
