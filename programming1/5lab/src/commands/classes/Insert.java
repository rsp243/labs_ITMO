package src.commands.classes;

import java.time.LocalDate;
import java.util.ArrayList;

import src.data.classes.City;
import src.data.classes.CollectionWorker;
import src.data.classes.Factories.CityFactory;
import src.streams.DataInOutStatus;

public class Insert extends Command {
    public Insert() {
        super("insert", "Add an element with typed key into the main collection", 10, CommandType.CITY_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        // extraArguments should be ArrayList<String> = [keyOfCity, nameOfCity,
        // strCoordinatesData, ...
        // ... srtArea, strPopulation, strMetersAboveSeaLevel, strTelephoneCode,
        // strCarCode, strClimate, strGovernor]
        
        System.out.println(extraArguments);
        String key = extraArguments.remove(0);
        City newCity = new CityFactory().createCity(extraArguments);

        // City newCity = new City(worker.getAutoIncrementedKey(), , null, null, null,
        // 0, 0, 0, null, null)
        worker.addNew(key, newCity);
        return "Successfully";
    }
}
