package server.data.Validators.HumanValidator;

import java.time.LocalDate;

import server.data.Validators.AbstractValidator;

public class BirthdayHumanValidator extends AbstractValidator<String>{

    public BirthdayHumanValidator() {
        super("City.Human.birthday", "Inputed format: DD.MM.YYYY, birthday can not be later today");
    }

    @Override
    public String caster(String str) {
        return super.caster(str);
    }

    @Override
    public boolean validate(String variable) {
        String[] dmy = variable.split("\\.");
        LocalDate birthday = LocalDate.of(Integer.parseInt(dmy[2]), Integer.parseInt(dmy[1]), Integer.parseInt(dmy[0]));
        if (birthday.isAfter(LocalDate.now())) {
            return false;
        }
        return variable.split("\\.").length == 3;
    }
}
