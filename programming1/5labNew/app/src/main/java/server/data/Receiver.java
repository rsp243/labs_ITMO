package server.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.crypto.KeyGenerator;

import client.MetaInfoCommand;
import client.streams.DataInOutStatus;
import client.streams.in.CommandDataChecker;
import client.streams.in.ExecutionMode;
import client.streams.in.File.ReaderCSV;
import server.commands.Command;
import server.data.Factories.CityFactory;
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

    public String removeGreater(ArrayList<String> extraArguments) {
        LinkedHashMap<String, City> mainCollection = getMainCollection();
        Long id = Long.parseLong("1");
        City newCity = new CityFactory().createCity(id, extraArguments);
        ArrayList<String> deletedKeys = new ArrayList<>();
        for (String key : mainCollection.keySet()) {
            if (mainCollection.get(key).compareTo(newCity) > 0) {
                deletedKeys.add(key);
            }
        }
        for (String key : deletedKeys) {
            this.removeKey(key);
        }
        return "Count of removed elements: " + deletedKeys.size() + ".";
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
    
    
    /**
     * Check correctness arguments of complicated command with > 1 extra arguments
     * 
     * @param commandName        Name of command
     * @param argumentsToCommand ArrayList of arguments typed in the same line as
     *                           command
     * @param execMode           ExecutionMode Enum value
     * @return status of correctness of readed data
     */
    public DataInOutStatus checkCorrectnessOfComplicatedCommand(Command commandObj,
    ArrayList<String> argumentsToCommand, ExecutionMode execMode) {
        if (argumentsToCommand.size() > 1) {
            return DataInOutStatus.SUCCESFULLY;
        }
        DataInOutStatus correctnessStatus = DataInOutStatus.SUCCESFULLY;
        LinkedHashMap<String, String> fields = MetaInfoCommand.getFields();
        CommandDataChecker commandChecker = new CommandDataChecker();
        correctnessStatus = commandChecker.checkInputedCommand(commandObj, argumentsToCommand, fields,
                execMode);
        if (correctnessStatus == DataInOutStatus.SUCCESFULLY) {
            argumentsToCommand.addAll(commandChecker.getExtraArguments());
        }        
        if (argumentsToCommand.size() == commandObj.getCountOfExtraInlineArgs()) {
            return DataInOutStatus.WRONGARGS;
        } else {
            return DataInOutStatus.SUCCESFULLY;
        }
    }

    public LocalDatabase getDataBase() {
        return database;
    }

    public Increment getUniqueID() {
        return uniqueID;
    }
}
