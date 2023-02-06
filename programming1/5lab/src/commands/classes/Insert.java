package src.commands.classes;

import src.data.classes.MainCollection;
import src.data.classes.Organisation;

public class Insert extends Command {
    private MainCollection mainCollection;
    private Organisation newOrganisation;
    private Integer keyOfOrg;

    public Insert(String aName, String aDescription, MainCollection aMainCollection, Organisation aNewOrganisation, Integer aKeyOfOrg) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
        newOrganisation = aNewOrganisation;
        keyOfOrg = aKeyOfOrg;
    }

    @Override
    public String execute() {
        return mainCollection.addNewOrgIntoCollection(keyOfOrg, newOrganisation);
    }
    
}
