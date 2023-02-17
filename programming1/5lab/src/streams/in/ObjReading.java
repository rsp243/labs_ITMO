package src.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import src.commands.classes.Command;
import src.commands.classes.CommandController;
import src.streams.DataInOutStatus;

public class ObjReading {
    public ObjReading() {}

    public ArrayList<String> objRead(CommandController commandController, String commandName, BufferedReader inpReader, DataInOutStatus dataFetchController, String inputData) throws IOException {
        ArrayList<String> extraArguments = new ArrayList<String>();
        if (commandController.getMapOfCommands().containsKey(commandName)) {
            Command commandObj = commandController.getMapOfCommands().get(commandName);
            if (commandObj.getCountOfExtraArgs() >= 1) {
                if (inputData.split(" ").length < 2) {
                    dataFetchController = DataInOutStatus.FAILED;
                } else {
                    String key = inputData.split(" ")[1];
                    extraArguments.add(key);
                    if (commandObj.getCountOfExtraArgs() > 1) {
                        System.out.println("Type extra data of object.");
                        for (Integer iter = 0; iter < commandObj.getCountOfExtraArgs() - 1; iter++) {
                            String extraInputData = inpReader.readLine();
                            if (extraInputData != null) {
                                extraArguments.add(extraInputData.trim());
                            }
                        }
                    }
                    if (extraArguments.size() != commandObj.getCountOfExtraArgs()) {
                        dataFetchController = DataInOutStatus.FAILED;
                    }
                }
            }
        }
        return extraArguments;
    }
}
