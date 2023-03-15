package server.data.classes.Validators.classes.HumanValidator;

import server.data.classes.Validators.classes.AbstractValidator;

public class HeightHumanValidator extends AbstractValidator<Float> {
    public HeightHumanValidator() {
        super("City.Human.height");
    }
    
    @Override
    public boolean validate(Float height) {
        return height > 0;
    }

    @Override
    public Float caster(String str) {
        return Float.parseFloat(str);
    }
}
