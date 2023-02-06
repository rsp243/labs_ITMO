package src.commands.classes;

import src.data.classes.MainCollection;
import src.data.classes.Organisation;

public class Update extends Command {
    private MainCollection mainCollection;
    private Organisation updatedOrganisation;
    private Integer keyOfOrg;

    public Update(String aName, String aDescription, MainCollection aMainCollection, Organisation aUpdatedOrganisation, Integer aKeyOfOrg) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
        updatedOrganisation = aUpdatedOrganisation;
        keyOfOrg = aKeyOfOrg;
    }
    
    @Override
    public String execute() {
        return mainCollection.updateOrg(keyOfOrg, updatedOrganisation);
    }
}
