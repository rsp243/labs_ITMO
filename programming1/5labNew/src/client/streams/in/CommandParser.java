package client.streams.in;

import java.util.ArrayList;

import client.streams.DataInOutStatus;

public class CommandParser {

    public DataInOutStatus parse(String inputData) {
        String[] splittedInputData = inputData.split(" ");
        String commandName = splittedInputData[0];
        ArrayList<String> commandArguments = new ArrayList<>();
        for (int i = 1; i < splittedInputData.length; i++) {
            commandArguments.add(splittedInputData[i]);
        }
        if (commandName != null) {
            return new CommandChecker().checkCorrectnessOfCommand(commandName, commandArguments);
        } else {
            return DataInOutStatus.FAILED;
        }
        // CommandDataChecker commandChecker = new CommandDataChecker();
        // if (commandChecker.checkInputedCommand(commandController, inpReader,
        // outputStream,
        // inputData, fields) == DataInOutStatus.FAILED) {
        // outputStream.outputIntoCLI("You have typed wrong arguments to last command.
        // Try again. \n");
        // } else {
        // ArrayList<String> extraArguments = commandChecker.getExtraArguments();
        // if (commandController.getMapOfCommands().containsKey(commandName)) {
        // Command commandObj = commandController.getMapOfCommands().get(commandName);
        // if (commandObj.getCountOfExtraArgs() >= 1) {
        // if (splittedInputData.length == 2) {
        // String key = inputData.split(" ")[1];
        // extraArguments.add(0, key);
        // }
        // }
        // }
        // outputStream.openOutputStream(commandController, fields, commandName,
        // extraArguments);
        // }
    }
}
