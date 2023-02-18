package src.commands.classes;

import java.util.ArrayList;

import src.data.classes.CollectionWorker;

public class Remove_key extends Command {
    public Remove_key() {
        super("remove_key", "Remove an element with typed key from the main collection", 1, CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
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
