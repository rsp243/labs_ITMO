package src.commands.classes;

import java.time.LocalDate;
import java.util.ArrayList;

import src.data.classes.City;
import src.data.classes.CollectionWorker;
import src.data.classes.Factories.CityFactory;

public class Insert extends Command {
    public Insert() {
        super("insert", "Add an element with typed key into the main collection", 10, CommandType.CITY_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        String resultStr = "Successfully";
        String key = extraArguments.remove(0);
        if (worker.getMainCollection().keySet().contains(key)) {
            resultStr = "You typed wrong key of object. There is the same in main collection. Failed.";
        }
        City newCity = new CityFactory().createCity(extraArguments);
        worker.addNew(key, newCity);
        return resultStr;
    }
}
