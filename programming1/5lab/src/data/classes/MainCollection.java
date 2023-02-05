package src.data.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import src.fillers.Increment;

public class MainCollection {
    private HashMap<Integer, Organisation> mainCollection;
    private Date dateOfInitialization;
    private Increment autoIncrementedKey = new Increment(1);

    public MainCollection(ArrayList<Organisation> arrayOfOrganisations) {
        mainCollection = new HashMap<>();
        for (Organisation org : arrayOfOrganisations) {
            mainCollection.put(autoIncrementedKey.getNewId() ,org);
        }
        dateOfInitialization = new Date();
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

    public void setDateOfInitialization(Date dateOfInitialization) {
        this.dateOfInitialization = dateOfInitialization;
    }

    public Integer getAutoIncrementedKey() {
        return autoIncrementedKey.getCurrentID();
    }

    @Override
    public String toString() {
        return "MainCollection [mainCollection=" + mainCollection + ", dateOfInitialization=" + dateOfInitialization
                + ", autoIncrementedKey=" + autoIncrementedKey + "]";
    }
}
