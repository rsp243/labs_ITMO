package src.commands.classes;

import java.time.LocalDate;
import java.util.ArrayList;

import src.commands.interfaces.ExtraArgsNeedable;
import src.data.classes.City;
import src.data.classes.CollectionWorker;
import src.streams.DataInOutStatus;

public class Insert extends Command implements ExtraArgsNeedable {
    public Insert() {
        super("insert", "Add an element with typed key into the main collection", 10, CommandType.CITY_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        if (getCountOfExtraArgs() > 1) {
            fetchExtraData();
        }
        // extraArguments should be ArrayList<String> = [keyOfCity, nameOfCity,
        // strCoordinatesData, ...
        // ... srtArea, strPopulation, strMetersAboveSeaLevel, strTelephoneCode,
        // strCarCode, strClimate, strGovernor]
        
        // City newCity = new City(worker.getAutoIncrementedKey(), , null, null, null,
        // 0, 0, 0, null, null)
        // worker.addNew(extraArguments.get(0), newCity);
        return "Successfully";
    }

    @Override
    public City fetchExtraData() {
        ArrayList<String> extraArguments = new ArrayList<String>();
        DataInOutStatus dataFetchController = DataInOutStatus.SUCCESFULLY;
        System.out.println("Type extra data of object.");
        City newCity = new City(null, getName(), null, null, getCountOfExtraArgs(), 0, 0, 0, null, null);
        return newCity;
    }
}
