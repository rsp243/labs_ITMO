package server.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.MetaInfoCommand;
import client.streams.in.ExecutionMode;
import server.data.City;
import server.data.Receiver;

/**
 * RemoveGreater Command
 * Remove an object from collection if typed value of some field less than object's value of that field.
 */

public class RemoveGreater extends Command {
    public RemoveGreater() {
        super("remove_greater", "Remove an object from collection if typed value of some field less than object's value of that field.", 2, CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        LinkedHashMap<String, City> mainCollection = worker.getMainCollection();
        String field = extraArguments.get(0);
        String value = extraArguments.get(1);
        LinkedHashMap<String, String> fields = MetaInfoCommand.getFields();
        int count = 0;
        int iter = 0;
        int valueNumber = -1;
        for (String keyOfFieldsMap : fields.keySet()){
            if (valueNumber == -1 && field.equals(keyOfFieldsMap)){
                valueNumber = iter;
            }
            iter++;
        }
        if (field.equals("key")) {
            return execution.append("Use command remove_greater_key " + value + ".").toString();
        }
        if (valueNumber == -1) {
            execution.append("You've typed wrong field to compare it. Use following construction to use remove_greter: remove_greater 'field' 'value'.");
            execution.append("\nPossible fields: " + fields.keySet().toString());
        } else {
            ArrayList<String> removeKeyArray = new ArrayList<>(); 
            for (String key : mainCollection.keySet()) {
                if (mainCollection.get(key).getAllFieldsValues().get(valueNumber).compareTo(value) < 0) {
                    removeKeyArray.add(key);
                    count++;
                }    
            }
            for (String key : removeKeyArray) {
                worker.removeKey(key);
            }
        }
        execution.append("\nCount of removed objects from collection with typed field '" + field + "': " + count + ".");
        return execution.toString();
    }
}
