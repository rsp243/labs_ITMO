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
    public DataInOutStatus checkCorrectnessOfCommand(String commandName, ArrayList<String> argumentsToCommand,
            ExecutionMode execMode) {
        MetaInfoCommand metaInfoObj = new MetaInfoCommand();
        Map<String, Command> mapOfCommands = metaInfoObj.getMapOfCommands();
        DataInOutStatus correctnessStatus = DataInOutStatus.SUCCESFULLY;
        if (mapOfCommands.containsKey(commandName)) {
            Command commandObj = mapOfCommands.get(commandName);
            if (commandObj.getCountOfExtraArgs() == 0 && argumentsToCommand.size() != 0) {
                return DataInOutStatus.WRONGARGS;
            }
            if (commandObj.getCountOfExtraArgs() >= 1) {
                correctnessStatus = checkComplicatedCommand(commandName, argumentsToCommand, commandObj, execMode);
            }
            if (correctnessStatus == DataInOutStatus.SUCCESFULLY) {
                OutCLIstream.outputIntoCLI(CommandController.execute(commandObj, argumentsToCommand, execMode), execMode);
            } else {
                return correctnessStatus;
            }
        } else {
            return DataInOutStatus.NOCOMMAND;
        }
        return DataInOutStatus.SUCCESFULLY;
    }

    public DataInOutStatus checkComplicatedCommand(String commandName, ArrayList<String> argumentsToCommand,
            Command commandObj, ExecutionMode execMode) {
        DataInOutStatus correctnessStatus = DataInOutStatus.SUCCESFULLY;
        if (commandObj.getCountOfExtraArgs() == 1 && argumentsToCommand.size() == 1) {
            correctnessStatus = DataInOutStatus.SUCCESFULLY;
            return correctnessStatus;
        }
        if (commandObj.getCountOfExtraArgs() > 1) {
            if (argumentsToCommand.size() == 1) {
                LinkedHashMap<String, String> fields = MetaInfoCommand.getFields();
                if (fields == null) {
                    MetaInfoCommand.setFields();
                    fields = MetaInfoCommand.getFields();
                }
                CommandDataChecker commandChecker = new CommandDataChecker();
                correctnessStatus = commandChecker.checkInputedCommand(commandObj, argumentsToCommand, fields,
                        execMode);
                if (correctnessStatus == DataInOutStatus.SUCCESFULLY) {
                    argumentsToCommand.addAll(commandChecker.getExtraArguments());
                }
            } else {
                if (argumentsToCommand.size() != commandObj.getCountOfExtraArgs()) {
                    return DataInOutStatus.WRONGARGS;
                }
            }
        } else {
            correctnessStatus = DataInOutStatus.WRONGARGS;
        }
        return correctnessStatus;
    }
}
