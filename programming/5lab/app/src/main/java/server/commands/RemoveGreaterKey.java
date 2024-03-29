package server.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.streams.DataInOutStatus;
import client.streams.in.ExecutionMode;
import server.data.City;
import server.data.Receiver;

/**
 * RemoveGreaterKey command
 * Remove an object from collection if typed key less than object's key.
 */

public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey() {
        super("remove_greater_key", "remove_greater_key key",
                "Remove objects from collection if typed key less than object's key.", 1,
                CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        int count = 0;
        LinkedHashMap<String, City> mainCollection = worker.getMainCollection();
        ArrayList<String> removeKeyArray = new ArrayList<>();
        for (String key : mainCollection.keySet()) {
            if (key.compareTo(extraArguments.get(0)) > 0) {
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
