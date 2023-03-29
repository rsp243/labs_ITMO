package server.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.MetaInfoCommand;
import client.streams.in.ExecutionMode;
import server.data.City;
import server.data.Receiver;

/**
 * RemoveGreater Command
 * Remove an object from collection if typed value of some field less than
 * object's value of that field.
 */

public class RemoveGreater extends Command {
    public RemoveGreater() {
        super("remove_greater", "remove_greater {element}",
                "Remove an object from collection if element less than object.", 0,
                CommandType.CITY_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        
        return execution.toString();
    }
}
