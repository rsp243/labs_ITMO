package client.streams.in;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.streams.DataInOutStatus;
import client.streams.in.CLI.ObjReading;
import server.commands.Command;

public class CommandDataChecker {
    private ArrayList<String> extraArguments;

    public ArrayList<String> getExtraArguments() {
        return extraArguments;
    }

    public DataInOutStatus checkInputedCommand(Command commandObj, ArrayList<String> arguments,
            LinkedHashMap<String, String> fields, ExecutionMode execMode) {
        extraArguments = new ObjReading().objRead(commandObj, fields, execMode);
        if (extraArguments.size() == 0) {
            return DataInOutStatus.WRONGARGS;
        }
        return DataInOutStatus.SUCCESFULLY;
    }
}
