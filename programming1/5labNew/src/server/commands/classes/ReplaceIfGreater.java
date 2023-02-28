package server.commands.classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

import org.apache.commons.collections4.comparators.ComparatorChain;

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

        // Nessesaty to add checking if new object is greater, than current2
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
                Comparator<City> comparator = new Comparator<City>() {
                    @Override
                    public int compare(City arg0, City arg1) {
                        
                        
                        return ;
                    }
                };
                if () {}
                    mainCollection.remove(key);
                return execution.append("Successfully").toString();
            } else {
                return execution.append("You've typed wrong agruments to the object's fields.").toString();
            }
        } else {
            execution.append(
                    "You've typed wrong key. There is no element in the main collection with that key. Try again.");
        }
        return execution.toString();
    }
}
