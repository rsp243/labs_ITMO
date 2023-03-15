package server.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;

import client.streams.in.ExecutionMode;
import server.data.classes.City;
import server.data.classes.Receiver;

/**
 * Show command
 * Output all elements from collection
 */ 

public class Show extends Command {
    public Show() {
        super("show", "Output all elements from collection", 0, CommandType.INFO_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        if (worker.getMainCollection().size() == 0) {
            return "There is no elements in main collection.";
        }
        Comparator<City> cityComparator = Comparator.comparingLong(City::getId);
        LinkedHashMap<String, City> mainCollection = worker.getMainCollection(); 
        City[] arrayCities = new City[mainCollection.size()];
        int iter = 0;
        for (String key : mainCollection.keySet()) {
            arrayCities[iter] = mainCollection.get(key);
            iter++;
        }
        Arrays.sort(arrayCities, cityComparator);
        
        for (City city : arrayCities) {
            for (String key : mainCollection.keySet()) {
                if (mainCollection.get(key).equals(city)) {
                    execution.append("key = " + key + " : " + city.toString() + "\n");
                }                
            }
        }
        return execution.delete(execution.toString().length() - 1, execution.toString().length()).toString();
    }

}
