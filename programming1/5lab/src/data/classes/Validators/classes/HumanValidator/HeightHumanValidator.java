package src.data.classes.Validators.classes.HumanValidator;

import src.data.classes.Validators.interfaces.ValidatorInterface;

public class HeightHumanValidator implements ValidatorInterface<Integer> {
    @Override
    public boolean validate(Integer height) {
        return height > 0;
    }
}
