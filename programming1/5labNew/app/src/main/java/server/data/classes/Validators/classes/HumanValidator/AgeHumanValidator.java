package server.data.classes.Validators.classes.HumanValidator;

import server.data.classes.Validators.classes.AbstractValidator;

public class AgeHumanValidator extends AbstractValidator<Integer> {
    public AgeHumanValidator() {
        super("City.Human.age");
    }
    
    @Override
    public boolean validate(Integer age) {
        return age > 0;
    }

    @Override
    public Integer caster(String str) {
        return Integer.parseInt(str);
    }
}
