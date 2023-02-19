package src.data.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import src.fillers.Increment;

public class LocalDatabase {
    private LinkedHashMap<String, City> mainCollection;
    private Increment autoIncrementedKey = new Increment(1);
    private final Date dateOfInitialization;
    private Date dateOfLastChange;

    public LocalDatabase(ArrayList<City> arrayOfCities) {
        mainCollection = new LinkedHashMap<>();
        for (City  city : arrayOfCities) {
            mainCollection.put(Integer.toString(autoIncrementedKey.getNewId()), city);
        }
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    public LinkedHashMap<String, City> getMainCollection() {
        return mainCollection;
    }

    public Date getDateOfInitialization() {
        return dateOfInitialization;
    }

    public void setDateOfLastChange(Date dateOfLastChange) {
        this.dateOfLastChange = dateOfLastChange;
    }

    public Integer getAutoIncrementedKey() {
        return autoIncrementedKey.getCurrentID();
    }

    public String getAllTextOrgs() {
        String strShowOrganisations = "";
        if (mainCollection.size() != 0) {
            for (String iter: mainCollection.keySet()) {
                strShowOrganisations += "Key = " + iter + ", " + mainCollection.get(iter).toString() + "\n";
            }
        } else {
            strShowOrganisations = "No elements in main collection";
        }
        return strShowOrganisations;
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
