package src.commands.classes;

import java.util.Date;
import java.util.HashMap;

import src.data.classes.Organisation;

public class Info extends Command {
    private HashMap<Integer, Organisation> mainCollection;
    private Date dateOfInitialization;

    public Info(String aName, String aDescription, HashMap<Integer, Organisation> aMainCollection, Date aDateOfInitialization) {
        super(aName, aDescription);
        mainCollection = aMainCollection;
        dateOfInitialization = aDateOfInitialization;
    }

    @Override
    public String execute() {
        String strInfo = "";
        //Type / Class of main collection
        strInfo += "Type = " + mainCollection.getClass() + "\n";
        //Date of Initialisation of main collection
        strInfo += "Date of initialisation = " + dateOfInitialization + "\n";
        //Size of main collection
        strInfo += "Size = " + mainCollection.size();
        return strInfo;
    }

    public HashMap<Integer, Organisation> getMainCollection() {
        return mainCollection;
    }

    public void setMainCollection(HashMap<Integer, Organisation> mainCollection) {
        this.mainCollection = mainCollection;
    }

    public Date getDateOfInitialization() {
        return dateOfInitialization;
    }
}
