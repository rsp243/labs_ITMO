package server.commands.classes;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.classes.Receiver;

/**
 * Clear command
 * Clear the main collection
 */
public class Clear extends Command {
    public Clear() {
        super("clear", "Clear the main collection", 0, CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        String resultStr = "Successfully";
        if (worker.getMainCollection().size() == 0) {
            resultStr = "The main collection is empty.";    
        }
        worker.clear();
        worker.setDateOfLastChange();
        return resultStr;
    }
}