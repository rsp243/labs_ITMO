package src.commands.classes;

import java.util.LinkedHashMap;

import src.data.classes.City;
import src.data.classes.LocalDatabase;

public class CollectionWorker {
    private final LocalDatabase database;

    public CollectionWorker(LocalDatabase aLocalDatabase) {
        database = aLocalDatabase;    
    }

    public LinkedHashMap<Integer, City> getMainCollection() {
        return database.getMainCollection();
    }

    public LocalDatabase getDataBase() {
        return database;
    }
}
