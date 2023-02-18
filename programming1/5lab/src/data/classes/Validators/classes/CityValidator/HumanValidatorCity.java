package src.data.classes.Validators.classes.CityValidator;

import src.data.classes.Human;
import src.data.classes.Validators.interfaces.ValidatorInterface;

public class HumanValidatorCity implements ValidatorInterface<Human>{
    @Override
    public boolean validate(Human variable) {
        return variable != null;
    }
}
