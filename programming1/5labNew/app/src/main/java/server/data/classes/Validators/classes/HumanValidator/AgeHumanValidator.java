package server.data.classes.Validators.classes.HumanValidator;

import server.data.classes.Validators.classes.AbstractValidator;
import server.data.classes.Validators.interfaces.ValidatorInterface;

public class AgeHumanValidator extends AbstractValidator implements ValidatorInterface<Integer> {
    public AgeHumanValidator() {
        super("City.Human.age");
    }
    
    @Override
    public boolean validate(Integer age) {
        return age > 0;
    }
}
