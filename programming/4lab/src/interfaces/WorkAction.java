package src.interfaces;

import src.enums.Controller;

public interface WorkAction {
    public Controller work(int timeOfWorking);
    public int getEarnedMoney(int timeOfWorking);
}
