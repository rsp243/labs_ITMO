package src.interfaces;

import src.classes.Meal;
import src.enums.Controller;

public interface EatAction {
    public Controller eat(Meal mealObj);
}
