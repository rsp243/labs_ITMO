package src.commands.classes;

import src.data.classes.MainCollection;

public class Info extends Command {
    private MainCollection mainCollection;

    public Info(String aName, String aDescription, MainCollection aMainCollection) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
    }

    @Override
    public String execute() {
        String strInfo = "";
        //Type / Class of main collection
        strInfo += "Type = " + mainCollection.getMainCollection().getClass() + "\n";
        //Size of main collection
        strInfo += "Size = " + mainCollection.getMainCollection().size() + "\n";
        //Date of Initialisation of main collection
        strInfo += "Date of initialisation = " + mainCollection.getDateOfInitialization() + "\n";
        //Date of last change of main collection
        strInfo += "Date of last change = " + mainCollection.getDateOfLastChange();
        return strInfo;
    }
}
