package server.data.classes.Validators.classes.HumanValidator;

import server.data.classes.Validators.interfaces.ValidatorInterface;

public class HumanValidator implements ValidatorInterface<String[]>{
    @Override
    public boolean validate(String[] args) {
        if (new AgeHumanValidator().validate(Integer.parseInt(args[0])) &&
        new HeightHumanValidator().validate(Float.parseFloat(args[1]))) {
            return true;
        }
        return false;
    }
}
