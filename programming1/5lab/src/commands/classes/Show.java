package src.commands.classes;

import java.util.HashMap;

import src.data.classes.Organisation;

public class Show extends Command {
    private HashMap<Integer, Organisation> mainCollection;

    public Show(String aName, String aDescription, HashMap<Integer, Organisation> aMainCollection) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
    }
    
    @Override
    public String execute() {
        String strShowOrganisations = "";
        if (mainCollection.size() != 0) {
            strShowOrganisations += mainCollection.get(1).toString();
            for (int iter = 2; iter <= mainCollection.size(); iter++) {
                strShowOrganisations += "\n" + mainCollection.get(iter).toString();
            }
        } else {
            strShowOrganisations = "No elements in main collection";
        }
        return strShowOrganisations;
    }   
}
