package server.commands.classes;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.classes.City;
import server.data.classes.CollectionWorker;
import server.data.classes.Factories.CityFactory;
import server.fillers.Increment;

public class Insert extends Command {
    Increment uniqueID;

    public void setUniqueID(Increment uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Insert() {
        super("insert", "Add an element with typed key into the main collection", 12, CommandType.CITY_WORKER);
        if (uniqueID == null) {
            uniqueID = new Increment((long) 1);
        }
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        String resultStr = "Successfully";
        String key = extraArguments.remove(0);
        if (worker.getMainCollection().keySet().contains(key)) {
            return resultStr = "You typed wrong key of object. There is the same key in main collection. Failed.";    
        }
        City newCity = new CityFactory().createCity(uniqueID.getNewId(), extraArguments);
        worker.addNew(key, newCity);
        worker.setDateOfLastChange();
        return resultStr;
    }
}
