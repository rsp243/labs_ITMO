package src.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import src.commands.classes.Command;
import src.commands.classes.CommandController;
import src.streams.DataInOutStatus;
import src.streams.out.OutCLIstream;

public class CommandDataChecker {
    private ArrayList<String> extraArguments;

    public DataInOutStatus checkInputedCommand(CommandController commandController, BufferedReader inpReader,
            OutCLIstream outputCLI, String inputData, HashMap<String, String> fields) throws IOException {
        DataInOutStatus dataFetchController = DataInOutStatus.SUCCESFULLY;
        String[] splittedInputData = inputData.split(" ");
        String commandName = splittedInputData[0];
        if (commandController.getMapOfCommands().containsKey(commandName)) {
            Command commandObj = commandController.getMapOfCommands().get(commandName);
            if (commandObj.getCountOfExtraArgs() >= 1) {
                if (splittedInputData.length != 2) {
                    return DataInOutStatus.FAILED;
                }
            }
            if (commandObj.getCountOfExtraArgs() > 1) {
                extraArguments = new ObjReading().objRead(commandController, inpReader, outputCLI, commandName,
                        inputData, fields);
                if (extraArguments.size() == 0) {
                    
                    return DataInOutStatus.FAILED;
                }
            }
        }
        return dataFetchController;
    }

    public ArrayList<String> getExtraArguments() {
        return extraArguments;
    }
}
