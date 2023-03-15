package server.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;

import client.streams.in.ExecutionMode;
import server.data.City;
import server.data.Receiver;

/**
 * PrintFieldDescendingMetersAboveSeaLevel command
 * Output all values of objects's metersAboveSeaLevel in descending order.
 */

public class PrintFieldDescendingMetersAboveSeaLevel extends Command {
    public PrintFieldDescendingMetersAboveSeaLevel() {
        super("print_field_descending_metetrs_above_sea_level", "Output all values of objects's metersAboveSeaLevel in descending order.", 0,
                CommandType.INFO_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        Comparator<City> metersAboveSeaLevel = Comparator.comparingInt(City::getMetersAboveSeaLevel).reversed();
        LinkedHashMap<String, City> mainCollection = worker.getMainCollection(); 
        City[] arrayCities = new City[mainCollection.size()];
        int iter = 0;
        for (String key : mainCollection.keySet()) {
            arrayCities[iter] = mainCollection.get(key);
            iter++;
        }
        Arrays.sort(arrayCities, metersAboveSeaLevel);
        for (City city : arrayCities) {
            execution.append(city.getMetersAboveSeaLevel() + " ");
        }
        return execution.toString();
    }
}
