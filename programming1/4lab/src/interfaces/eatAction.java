package src.interfaces;

import src.classes.Meal;
import src.enums.Controller;

public interface eatAction {
    public Controller eat(Meal mealObj);
}
