package src.server.data.classes.Validators.classes.HumanValidator;

import src.server.data.classes.Validators.interfaces.ValidatorInterface;

public class HeightHumanValidator implements ValidatorInterface<Float> {
    @Override
    public boolean validate(Float height) {
        return height > 0;
    }
}
