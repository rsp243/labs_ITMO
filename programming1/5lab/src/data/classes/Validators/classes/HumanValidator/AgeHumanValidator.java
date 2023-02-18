package src.data.classes.Validators.classes.HumanValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class AgeHumanValidator implements ValidatorInterface<Integer> {
    @Override
    public boolean validate(Integer age) {
        return age > 0;
    }
}
