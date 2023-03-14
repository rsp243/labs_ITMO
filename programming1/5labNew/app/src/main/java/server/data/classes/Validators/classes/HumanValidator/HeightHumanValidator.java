package server.data.classes.Validators.classes.HumanValidator;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.classes.Validators.interfaces.ValidatorInterface;

public class HeightHumanValidator extends AbstractValidator implements ValidatorInterface<Float> {
    public HeightHumanValidator() {
        super("City.Human.height");
    }
    
    @Override
    public boolean validate(Float height) {
        return height > 0;
    }
}
