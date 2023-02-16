package src.data.classes;

import java.util.LinkedHashMap;

public class CollectionWorker {
    private final LocalDatabase database;

    public CollectionWorker(LocalDatabase aLocalDatabase) {
        database = aLocalDatabase;    
    }

    public LinkedHashMap<String, City> getMainCollection() {
        return database.getMainCollection();
    }

    public String addNew(String key, City city) {
        String strStatus = "Successufully";
        if (this.getMainCollection().keySet().contains(key)) {
            strStatus = "There is an object in main collection with that name. Try again with different keyName.";
        }
        this.getMainCollection().put(key, city);
        return strStatus;
    }

    public LocalDatabase getDataBase() {
        return database;
    }
}
