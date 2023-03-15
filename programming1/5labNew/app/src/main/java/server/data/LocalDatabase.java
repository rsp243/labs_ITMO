package server.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * LocalDatabase class.
 * Storage of main collections
 */

public class LocalDatabase {
    /**
     * MainCollection storage
     */
    private LinkedHashMap<String, City> mainCollection;
    /**
     * Date of initialisation of main collection
     */    
    private final Date dateOfInitialization;
    /**
     * Date of last change of main collection 
     */
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
