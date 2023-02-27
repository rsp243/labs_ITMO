package server.data.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class LocalDatabase {
    private LinkedHashMap<String, City> mainCollection;
    private final Date dateOfInitialization;
    private Date dateOfLastChange;

    public LocalDatabase(ArrayList<City> arrayOfCities) {
        mainCollection = new LinkedHashMap<>();
        dateOfInitialization = new Date();
        dateOfLastChange = new Date();
    }

    public void setMainCollection(LinkedHashMap<String, City> mainCollection) {
        this.mainCollection = mainCollection;
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

    public Date getDateOfLastChange() {
        return dateOfLastChange;
    }

    @Override
    public String toString() {
        return "LocalDatabase [mainCollection=" + mainCollection + ", dateOfInitialization=" + dateOfInitialization
                + ", dateOfLastChange=" + dateOfLastChange + "]";
    }

}
