package client.streams.in;

import java.util.ArrayList;

import client.streams.DataInOutStatus;

public class CommandParser {

    public DataInOutStatus parse(String inputData, ExecutionMode execMode) {
        String[] splittedInputData = inputData.split(" ");
        String commandName = splittedInputData[0];
        ArrayList<String> commandArguments = new ArrayList<>();
        for (int i = 1; i < splittedInputData.length; i++) {
            commandArguments.add(splittedInputData[i]);
        }
        if (commandName != null) {
            return new CommandChecker().checkCorrectnessOfCommand(commandName, commandArguments, execMode);
        } else {
            return DataInOutStatus.FAILED;
        }
    }
}
