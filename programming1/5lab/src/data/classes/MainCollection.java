package src.data.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import src.fillers.Increment;

public class MainCollection {
    private LinkedHashMap<Integer, City> mainCollection;
    private Increment autoIncrementedKey = new Increment(1);
    private Date dateOfInitialization;
    private Date dateOfLastChange;


    public MainCollection(ArrayList<City> arrayOfCities) {
        mainCollection = new LinkedHashMap<>();
        for (City  city : arrayOfCities) {
            mainCollection.put(autoIncrementedKey.getNewId(), city);
        }
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    public LinkedHashMap<Integer, City> getMainCollection() {
        return mainCollection;
    }

    public void setMainCollection(LinkedHashMap<Integer, City> mainCollection) {
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

    public String addNewOrg(Integer key, City city) {
        String strSuccess = "Successufully";
        mainCollection.put(key, city);
        return strSuccess;
    }
    
    public String updateOrg(Integer key, City city) {
        String strSuccess = "Successufully";
        if (mainCollection.containsKey(key)) {
            mainCollection.replace(key, city);
        } else {
            strSuccess = "Failed";
        }
        return strSuccess;
    }

    public String removeOrg(Integer key) {
        String strSuccess = "Successufully";
        if (mainCollection.containsKey(key)) {
            mainCollection.remove(key);
        } else {
            strSuccess = "Failed";
        }
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
