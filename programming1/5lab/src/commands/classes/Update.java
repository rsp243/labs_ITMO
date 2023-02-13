package src.commands.classes;

import src.data.classes.City;
import src.data.classes.MainCollection;

public class Update extends Command {
    private MainCollection mainCollection;
    private City updatedCity;
    private Integer keyOfCity;

    public Update(String aName, String aDescription, MainCollection aMainCollection, City anUpdatedCity, Integer aKeyOfCity) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
        updatedCity = anUpdatedCity;
        keyOfCity = aKeyOfCity;
    }
    
    @Override
    public String execute() {
        return mainCollection.updateOrg(keyOfCity, updatedCity);
    }
}
