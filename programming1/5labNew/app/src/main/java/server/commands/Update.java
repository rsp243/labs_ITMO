package server.commands;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.City;
import server.data.Receiver;
import server.data.Factories.CityFactory;

/**
 * Update command
 * Update an element with typed key in the main collection
 */

public class Update extends Command {
    public Update() {
        super("update", "update id {element}", "Update an element with typed id in the main collection", 1,
                CommandType.CITY_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        String resultStr = "Successfully";
        Long id = Long.parseLong(extraArguments.remove(0));
        if (!worker.getIds().contains(id)) {
            return resultStr = "You typed wrong key of object. There is no objects in main collection with that id. Failed.";
        } else {
            City newCity = new CityFactory().createCity(id, extraArguments);
            worker.update(id, newCity);
        }
        return resultStr;
    }
}
