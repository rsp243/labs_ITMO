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
        String nameOfCity = extraArguments.get(1);
        String strCoordinatesData = extraArguments.get(2);
        LocalDate creationDate = LocalDate.now();
        // strArea need to be casted to Long type
        String srtArea = extraArguments.get(3);
        // strPopulation need to be casted to Integer type
        String strPopulation = extraArguments.get(4);
        // strMetersAboveSeaLevel need to be casted to int type
        String strMetersAboveSeaLevel = extraArguments.get(5);
        // strTelephoneCode need to be casted to int type
        String strTelephoneCode = extraArguments.get(6);
        // strCarCode need to be casted to int type
        String strCarCode = extraArguments.get(7);
        // strClimate need to be casted to Climate type
        String strClimate = extraArguments.get(8);
        // strGovernor need to be casted to Human type
        String strGovernor = extraArguments.get(9);
        System.out.println(nameOfCity + strCoordinatesData + srtArea + strPopulation + strMetersAboveSeaLevel
                + strTelephoneCode + strCarCode + strClimate + strGovernor);

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
