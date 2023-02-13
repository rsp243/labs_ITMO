package src.commands.classes;

import src.data.classes.City;
import src.data.classes.MainCollection;

public class Insert extends Command {
    private MainCollection mainCollection;
    private City newCity;
    private Integer keyOfOrg;

    public Insert(String aName, String aDescription, MainCollection aMainCollection, City aNewCity, Integer aKeyOfCity) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
        newCity = aNewCity;
        keyOfOrg = aKeyOfCity;
    }

    @Override
    public String execute() {
        return mainCollection.addNewOrg(keyOfOrg, newCity);
    }
}
