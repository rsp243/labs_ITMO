package server.commands.classes;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.classes.Receiver;

/**
 * RemoveKey command
 * Remove an element with typed key from the main collection
 */

public class RemoveKey extends Command {
    public RemoveKey() {
        super("remove_key", "Remove an element with typed key from the main collection", 1, CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        String resultStr;
        String key = extraArguments.remove(0);
        if (worker.getMainCollection().keySet().contains(key)) {
            resultStr = worker.removeKey(key);
        } else {
            resultStr = "You typed wrong key of object. There is no objects in main collection with that key. Failed.";    
        }
        return resultStr;
    }
}
