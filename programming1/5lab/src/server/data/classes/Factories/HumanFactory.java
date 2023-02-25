package src.server.data.classes.Factories;

import java.time.LocalDate;

import src.server.data.classes.Human;

public class HumanFactory {
    public Human createHumanObj(String[] args) {
        Integer age = Integer.parseInt(args[0]);
        Float height = Float.parseFloat(args[1]);
        String[] dmy = args[2].split("\\.");
        LocalDate birthday = LocalDate.of(Integer.parseInt(dmy[2]), Integer.parseInt(dmy[1]), Integer.parseInt(dmy[0]));
        return new Human(age, height, birthday);
    }
}
