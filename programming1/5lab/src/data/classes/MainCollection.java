package src.data.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import src.fillers.Increment;

public class MainCollection {
    private HashMap<Integer, Organisation> mainCollection;
    private Increment autoIncrementedKey = new Increment(1);
    private Date dateOfInitialization;
    private Date dateOfLastChange;


    public MainCollection(ArrayList<Organisation> arrayOfOrganisations) {
        mainCollection = new HashMap<>();
        for (Organisation org : arrayOfOrganisations) {
            mainCollection.put(autoIncrementedKey.getNewId(), org);
        }
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
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

    public String getAllTextOrgs() {
        String strShowOrganisations = "";
        if (mainCollection.size() != 0) {
            for (Integer iter: mainCollection.keySet()) {
                strShowOrganisations += "Key = " + iter + ", " + mainCollection.get(iter).toString() + "\n";
            }
        } else {
            strShowOrganisations = "No elements in main collection";
        }
        return strShowOrganisations;
    }

    public String addNewOrgIntoCollection(Integer key, Organisation org) {
        String strSuccess = "Successufully";
        mainCollection.put(key, org);
        return strSuccess;
    }

    @Override
    public String toString() {
        return "MainCollection [mainCollection=" + mainCollection + ", dateOfInitialization=" + dateOfInitialization
                + ", autoIncrementedKey=" + autoIncrementedKey + "]";
    }

    public Date getDateOfLastChange() {
        return dateOfLastChange;
    }
}
