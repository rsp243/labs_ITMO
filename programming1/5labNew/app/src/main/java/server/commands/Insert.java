package server.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.MetaInfoCommand;
import client.streams.DataInOutStatus;
import client.streams.in.CommandDataChecker;
import client.streams.in.ExecutionMode;
import server.data.City;
import server.data.Receiver;
import server.data.Factories.CityFactory;
import server.fillers.Increment;

/**
 * Insert Commmand
 * Add an element with typed key into the main collection
 */

public class Insert extends Command implements ComplicatedCommandValidatorable {
    /**
     * uniqueID used to create IDs of objects
     */
    private Increment uniqueID;

    public void setUniqueID(Increment uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Insert() {
        super("insert", "insert key {element}", "Add an element with typed key into the main collection", 1,
                CommandType.CITY_WORKER);
        if (uniqueID == null) {
            uniqueID = new Increment((long) 1);
        }
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        String resultStr = "Successfully";
        String key = extraArguments.get(0);
        if (worker.getMainCollection().keySet().contains(key)) {
            return resultStr = "You typed wrong key of object. There is the same key in main collection. Failed.";
        }
        DataInOutStatus correctnessStatus = checkCorrectnessOfComplicatedCommand(this, extraArguments, execMode);
        if (correctnessStatus == DataInOutStatus.SUCCESFULLY) {
            extraArguments.remove(0);
            City newCity = new CityFactory().createCity(uniqueID.getNewId(), extraArguments);
            worker.addNew(key, newCity);
            worker.setDateOfLastChange();
            return resultStr;
        }
        return correctnessStatus.getMessage();
    }

    @Override
    public DataInOutStatus checkCorrectnessOfComplicatedCommand(Command commandObj,
            ArrayList<String> argumentsToCommand, ExecutionMode execMode) {
        DataInOutStatus correctnessStatus = DataInOutStatus.SUCCESFULLY;
        LinkedHashMap<String, String> fields = MetaInfoCommand.getFields();
        CommandDataChecker commandChecker = new CommandDataChecker();
        correctnessStatus = commandChecker.checkInputedCommand(commandObj, argumentsToCommand, fields,
                execMode);
        if (correctnessStatus == DataInOutStatus.SUCCESFULLY) {
            argumentsToCommand.addAll(commandChecker.getExtraArguments());
        }        
        if (argumentsToCommand.size() == this.getCountOfExtraInlineArgs()) {
            return DataInOutStatus.WRONGARGS;
        } else {
            return DataInOutStatus.SUCCESFULLY;
        }
    }
}
