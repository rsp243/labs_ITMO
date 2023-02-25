package client.streams.in;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.streams.DataInOutStatus;
import server.commands.classes.Command;

public class CommandDataChecker {
    private ArrayList<String> extraArguments;

    public ArrayList<String> getExtraArguments() {
        return extraArguments;
    }

    public DataInOutStatus checkInputedCommand(Command commandObj, ArrayList<String> arguments,
            LinkedHashMap<String, String> fields) {
        extraArguments = new ObjReading().objRead(commandObj, fields);
        System.out.println(extraArguments.toString());
        if (extraArguments.size() == 0) {
            return DataInOutStatus.FAILED;
        }
        return DataInOutStatus.SUCCESFULLY;
    }
}
