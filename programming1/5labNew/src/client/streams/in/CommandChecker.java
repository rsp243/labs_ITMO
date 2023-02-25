package client.streams.in;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import client.MetaInfoCommand;
import client.streams.DataInOutStatus;
import client.streams.out.OutCLIstream;
import server.commands.classes.Command;
import server.commands.classes.CommandController;

public class CommandChecker {

    public DataInOutStatus checkCorrectnessOfCommand(String commandName, ArrayList<String> argumentsToCommand) {
        MetaInfoCommand metaInfoObj = new MetaInfoCommand();
        Map<String, Command> mapOfCommands = metaInfoObj.getMapOfCommands();
        DataInOutStatus correctnessStatus = DataInOutStatus.SUCCESFULLY;
        if (mapOfCommands.containsKey(commandName)) {
            Command commandObj = mapOfCommands.get(commandName);
            if (commandObj.getCountOfExtraArgs() == 0 && argumentsToCommand.size() != 0) {
                return DataInOutStatus.WRONGARGS;
            }
            if (mapOfCommands.get(commandName).getCountOfExtraArgs() >= 1) {
                if (mapOfCommands.get(commandName).getCountOfExtraArgs() > 1 && argumentsToCommand.size() == 1) {
                    LinkedHashMap<String, String> fields = metaInfoObj.getFields();
                    if (fields == null) {
                        metaInfoObj.setFields();
                        fields = metaInfoObj.getFields();
                    }
                    CommandDataChecker commandChecker = new CommandDataChecker();
                    correctnessStatus = commandChecker.checkInputedCommand(commandObj, argumentsToCommand, fields);
                    if (correctnessStatus == DataInOutStatus.SUCCESFULLY) {
                        argumentsToCommand.addAll(commandChecker.getExtraArguments());
                    }
                } else {
                    return DataInOutStatus.WRONGARGS;
                }
                if (argumentsToCommand.size() == 1) {
                    return DataInOutStatus.WRONGARGS;
                }

            }
            if (correctnessStatus == DataInOutStatus.SUCCESFULLY) {
                OutCLIstream.outputIntoCLI(CommandController.execute(commandObj, argumentsToCommand));
            }
        } else {
            return DataInOutStatus.NOCOMMAND;
        }
        return DataInOutStatus.SUCCESFULLY;
    }
}
