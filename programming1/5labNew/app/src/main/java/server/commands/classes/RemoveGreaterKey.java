package server.commands.classes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.streams.in.ExecutionMode;
import server.data.classes.City;
import server.data.classes.Receiver;

/**
 * RemoveGreaterKey command
 * Remove an object from collection if typed key less than object's key.
 */

public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey() {
        super("remove_greater_key", "Remove an object from collection if typed key less than object's key.", 1,
                CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        LinkedHashMap<String, City> mainCollection = worker.getMainCollection();
        int count = 0;
        ArrayList<String> removeKeyArray = new ArrayList<>(); 
        for (String key : mainCollection.keySet()) {
            if (key.compareTo(extraArguments.get(0)) < 0) {
                removeKeyArray.add(key);
                count++;
            }    
        }
        for (String key : removeKeyArray) {
            worker.removeKey(key);
        }
        execution.append("Count of removed objects from collection: " + count + ".");
        return execution.toString();
    }
}
