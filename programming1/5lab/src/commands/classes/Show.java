package src.commands.classes;

import src.data.classes.MainCollection;

public class Show extends Command {
    private MainCollection mainCollection;

    public Show(String aName, String aDescription, MainCollection aMainCollection) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
    }
    
    @Override
    public String execute() {
        return mainCollection.getAllTextOrgs();
    }   
}
