package server.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.MetaInfoCommand;
import client.streams.in.ExecutionMode;
import client.streams.in.CLI.ObjReading;
import server.data.City;
import server.data.Receiver;
import server.data.Factories.CityFactory;

/**
 * ReplaceIfGreater command
 */

public class ReplaceIfGreater extends Command {

    public ReplaceIfGreater() {
        super("replace_if_greater", "replace_if_greater key {element}",
                "Replace object in main collection with typed key if typed element more than current.", 1,
                CommandType.CITY_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        LinkedHashMap<String, City> mainCollection = worker.getMainCollection();
        String key = extraArguments.get(0);
        if (mainCollection.containsKey(key)) {
            LinkedHashMap<String, String> fields = MetaInfoCommand.getFields();
            fields = MetaInfoCommand.getFields();
            extraArguments = new ObjReading().objRead(this, fields, execMode);
            if (extraArguments.size() != 0) {
                Long id = mainCollection.get(key).getId();
                City newCityObj = new CityFactory().createCity(id, extraArguments);
                if (mainCollection.get(key).compareTo(newCityObj) < 0) {
                    mainCollection.remove(key);
                    mainCollection.put(key, newCityObj);
                } else
                    return execution.append("Field's values of typed object less than current. Not replaced.")
                            .toString();
                return execution.append("Successfully").toString();
            } else {
                return execution.append("You've typed wrong agruments to the object's fields. Failed.").toString();
            }
        } else {
            execution.append(
                    "You've typed wrong key. There is no element in the main collection with that key. Try again.");
        }
        return execution.toString();
    }
}
