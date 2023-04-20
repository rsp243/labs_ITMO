package server.data.Validators.HumanValidator;

import server.data.Validators.AbstractValidator;

public class AgeHumanValidator extends AbstractValidator<Integer> {
    public AgeHumanValidator() {
        super("City.Human.age", "more than 0");
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
