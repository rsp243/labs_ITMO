package src.server.commands.classes;

import java.util.ArrayList;

import src.server.data.classes.City;
import src.server.data.classes.CollectionWorker;
import src.server.data.classes.Factories.CityFactory;

public class Update extends Command {
    public Update() {
        super("update", "Update an element with typed key in the main collection", 10, CommandType.CITY_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        String resultStr = "Successfully";
        String key = extraArguments.remove(0);
        if (!worker.getMainCollection().keySet().contains(key)) {
            resultStr = "You typed wrong key of object. There is no objects in main collection with that key. Failed.";       
        }
        City newCity = new CityFactory().createCity(extraArguments);
        worker.addNew(key, newCity);
        worker.setDateOfLastChange();
        return resultStr;
    }
}
