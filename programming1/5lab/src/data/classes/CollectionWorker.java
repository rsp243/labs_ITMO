package src.data.classes;

import java.util.Date;
import java.util.LinkedHashMap;

import src.fillers.Increment;

public class CollectionWorker {
    private final LocalDatabase database;
    private Increment autoIncrementedKey = new Increment(1);

    public Increment getAutoIncrementedKey() {
        return autoIncrementedKey;
    }

    public CollectionWorker(LocalDatabase aLocalDatabase) {
        database = aLocalDatabase;
    }

    public LinkedHashMap<String, City> getMainCollection() {
        return database.getMainCollection();
    }

    public String addNew(String key, City city) {
        String strStatus = "Successufully";
        this.getMainCollection().put(key, city);
        return strStatus;
    }

    public String removeKey(String key) {
        String strSuccess = "Successufully";
        this.getMainCollection().remove(key);
        return strSuccess;
    }

    public void setDateOfLastChange() {
        database.setDateOfLastChange(new Date());
    }

    public LocalDatabase getDataBase() {
        return database;
    }
}
