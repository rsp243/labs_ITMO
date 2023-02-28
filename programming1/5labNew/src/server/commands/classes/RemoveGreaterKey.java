package server.commands.classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

import server.data.classes.City;
import server.data.classes.CollectionWorker;

public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey() {
        super("remove_greater_key", "Remove an object from collection if typed key less than object's key.", 1,
                CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments) {
        StringBuilder execution = new StringBuilder();
        LinkedHashMap<String, server.data.classes.City> mainCollection = worker.getMainCollection();
        int count = 0;
        for (String key : mainCollection.keySet()) {
            if (key.compareTo(extraArguments.get(0)) < 0) {
                mainCollection.remove(key);
                count++;
            }    
        }
        execution.append("Count of removed objects from collection: " + count + ".");
        return execution.toString();
    }
}
