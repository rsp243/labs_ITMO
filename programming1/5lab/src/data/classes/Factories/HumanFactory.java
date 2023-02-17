package src.data.classes.Factories;

import java.util.Date;

import src.data.classes.Human;

public class HumanFactory {
    public HumanFactory() {}

    public Human createHumanObj(String[] args) {
        Integer age = Integer.parseInt(args[0]);
        Integer height = Integer.parseInt(args[1]);
        Date birthday = new Date();
        return new Human(age, height, birthday);
    }
}
