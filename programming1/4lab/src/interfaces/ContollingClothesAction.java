package src.interfaces;

import src.classes.Clothes;
import src.enums.Controller;

public interface ContollingClothesAction {
    public Controller putOnClothes(Clothes clothes);
    public Controller takeOffClothes(Clothes clothes);
}
