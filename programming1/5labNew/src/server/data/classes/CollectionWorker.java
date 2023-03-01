package server.data.classes;

import java.util.Date;
import java.util.LinkedHashMap;

import client.streams.in.File.ReaderCSV;
import server.fillers.Increment;

/**
 * Collection Worker. Class that can work with storage - with LocalDatabase.
 * When created, new objects of @see City classes have added into the main collection
 */

public class CollectionWorker {
    private final LocalDatabase database;
    /*
     * Declaring @see Increment to have access to the unique ID number
     */
    private Increment uniqueID;
    
    public CollectionWorker(LocalDatabase aLocalDatabase) {
        database = aLocalDatabase;
        var CSVReader = new ReaderCSV();
        database.setMainCollection(CSVReader.getSavedCollection());
        uniqueID = CSVReader.getUniqueID();
    }

    /**
     * getMainCollection method.
     * @return main Collection from @see Database
     */
    public LinkedHashMap<String, City> getMainCollection() {
        return database.getMainCollection();
    }

    /**
     * addNew method - add a new city into the main collection with key.
     * @param key key in main collection to the value - @see City
     * @param city value - object of @see City
     * @return strStatus = Successfully
     */
    public String addNew(String key, City city) {
        String strStatus = "Successufully";
        this.getMainCollection().put(key, city);
        return strStatus;
    }

    /**
     * removeKey method - remove from the main collection a value with key.
     * @param key key of values from the main colelction
     * @return strStatus = Successfully
     */
    public String removeKey(String key) {
        String strSuccess = "Successufully";
        this.getMainCollection().remove(key);
        return strSuccess;
    }

    /**
     * update method - update the object @see City in the main collection by key.
     * @param key key of values from the main colelction 
     * @param city value - object of @see City
     * @return strStatus = Successfully
     */
    public String update(String key, City city) {
        String strSuccess = "Successufully";
        if (this.getMainCollection().containsKey(key)) {
            this.getMainCollection().replace(key, city);
        }
        return strSuccess;
    }

    /**
     * Clear method - clear the main collection.
     * @return strStatus = Successfully
     */
    public String clear() {
        String strSuccess = "Successufully";
        this.getMainCollection().clear();
        return strSuccess;
    }
    
    /**
     * sum_of_car_codes method - count the carCode values of objects from  main collection.
     * @return strStatus = Successfully
     */
    public Long sum_of_car_code() {
        Long sumCarCode = (long) 0;
        for (City iter : this.getMainCollection().values()) {
            sumCarCode += iter.getCarCode();
        }
        return sumCarCode;
    }

    /**
     * Set up Date of last change.
     */
    public void setDateOfLastChange() {
        database.setDateOfLastChange(new Date());
    }

    public LocalDatabase getDataBase() {
        return database;
    }

    public LocalDatabase getDatabase() {
        return database;
    }

    public Increment getUniqueID() {
        return uniqueID;
    }
}
