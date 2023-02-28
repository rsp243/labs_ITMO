package server.commands.classes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.MetaInfoCommand;
import client.streams.in.ObjReading;
import server.data.classes.City;
import server.data.classes.CollectionWorker;
import server.data.classes.Factories.CityFactory;

public class ReplaceIfGreater extends Command {
    public ReplaceIfGreater() {
        super("remove_if_greater", "Replace key of object in main collection if typed key more than current.", 1,
                CommandType.CITY_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments) {
        StringBuilder execution = new StringBuilder();
        LinkedHashMap<String, City> mainCollection = worker.getMainCollection();
        String key = extraArguments.get(0);
        if (mainCollection.containsKey(key)) {
            LinkedHashMap<String, String> fields = MetaInfoCommand.getFields();
            if (fields == null) {
                MetaInfoCommand.setFields();
                fields = MetaInfoCommand.getFields();
            }
            extraArguments = new ObjReading().objRead(this, fields);
            if (extraArguments.size() != 0) {
                Long id = mainCollection.get(key).getId();
                City newCityObj = new CityFactory().createCity(id, extraArguments);
                if (mainCollection.get(key).compareTo(newCityObj) < 0) {
                    mainCollection.remove(key);
                    mainCollection.put(key, newCityObj);
                } else return execution.append("Field's values of typed object less than current. Not replaced.").toString();
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
