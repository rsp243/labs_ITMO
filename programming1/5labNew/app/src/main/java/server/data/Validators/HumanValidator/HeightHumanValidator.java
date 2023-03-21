package server.data.Validators.HumanValidator;

import server.data.Validators.AbstractValidator;

public class HeightHumanValidator extends AbstractValidator<Float> {
    public HeightHumanValidator() {
        super("City.Human.height", "more than 0");
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
