package server.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import client.streams.in.File.ReaderCSV;
import server.fillers.Increment;

/**
 * Collection Worker. Class that can work with storage - with LocalDatabase.
 * When created, new objects of @see City classes have added into the main collection
 */

public class Receiver {
    private final LocalDatabase database;
    /*
     * Declaring @see Increment to have access to the unique ID number
     */
    private Increment uniqueID;
    
    public Receiver(LocalDatabase aLocalDatabase) {
        database = aLocalDatabase;
        var readerCSV = new ReaderCSV();
        database.setMainCollection(readerCSV.getSavedCollection());
        uniqueID = readerCSV.getUniqueID();
    }

    /**
     * getMainCollection method.
     * @return main Collection from @see Database
     */
    public LinkedHashMap<String, City> getMainCollection() {
        return database.getMainCollection();
    }

    public Set<Long> getIds() {
        Set<Long> resultMap = new HashSet<>(); 
        for (City element : getMainCollection().values()) {
            resultMap.add(element.getId());
        }
        return resultMap;
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
    public String update(Long id, City city) {
        String strSuccess = "Successufully";
        LinkedHashMap<String, City> mainCollection = getMainCollection();
        for (String key : mainCollection.keySet()) {
            if (mainCollection.get(key).getId() == id) {
                getMainCollection().put(key, city);
            }
        }
        this.setDateOfLastChange();
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
    public Long getSumOfCarCode() {
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

    public Increment getUniqueID() {
        return uniqueID;
    }
}
