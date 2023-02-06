package src.commands.classes;

import src.data.classes.MainCollection;
import src.data.classes.Organisation;

public class Remove_key extends Command {
    private MainCollection mainCollection;
    private Integer keyOfOrg;

    public Remove_key(String aName, String aDescription, MainCollection aMainCollection, Integer aKeyOfOrg) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
        keyOfOrg = aKeyOfOrg;
    }

    @Override
    public String execute() {
        return mainCollection.removeOrg(keyOfOrg);
    }
}
