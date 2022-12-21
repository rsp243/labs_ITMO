package src.classes;

import src.enums.mealType;

public class Meal {
    private String name;
    private mealType mealType;

    public Meal(String aName, mealType aMealType) {
        this.name = aName;
        this.mealType = aMealType;
    }

    public String getName() {
        return name;
    }

    public mealType getMealType() {
        return mealType;
    }

}
