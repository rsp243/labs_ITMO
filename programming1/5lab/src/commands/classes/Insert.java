package src.commands.classes;

import src.data.classes.City;
import src.data.classes.MainCollection;

public class Insert extends Command {
    public Insert() {
        super("insert", "Add an element with typed key into the main collection");
    }

    @Override
    public String execute() {
        return mainCollection.addNewOrg(keyOfOrg, newCity);
    }
}
