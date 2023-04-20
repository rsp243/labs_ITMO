package client.streams.in;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import client.MetaInfoCommand;
import client.streams.DataInOutStatus;
import client.streams.out.OutStream;
import server.commands.Command;
import server.commands.CommandType;
import server.commands.Invoker;

/**
 * CommandChecker Class
 * Check correcntess arguments of command
 */

public class CommandChecker {
    /**
     * Check correctness arguments of simple command with <= 1 extra arguments
     * 
     * @param commandName        Name of command
     * @param argumentsToCommand ArrayList of arguments typed in the same line as
     *                           command
     * @param execMode           ExecutionMode Enum value
     * @return
     */
    public DataInOutStatus checkCorrectnessOfCommand(String commandName, ArrayList<String> argumentsToCommand,
            ExecutionMode execMode) {
        MetaInfoCommand metaInfoObj = new MetaInfoCommand();
        Map<String, Command> mapOfCommands = metaInfoObj.getMapOfCommands();
        DataInOutStatus correctnessStatus = DataInOutStatus.SUCCESFULLY;
        if (mapOfCommands.containsKey(commandName)) {
            Command commandObj = mapOfCommands.get(commandName);
            if (commandObj.getCountOfExtraInlineArgs() == 0 && argumentsToCommand.size() != 0) {
                return DataInOutStatus.WRONGARGS;
            }
            if (commandObj.getCountOfExtraInlineArgs() == 1) {
                if (argumentsToCommand.size() != 1) {
                    return DataInOutStatus.WRONGARGS;
                }
            }
            if (correctnessStatus == DataInOutStatus.SUCCESFULLY) {
                OutStream.outputIntoCLI(Invoker.invoke(commandObj, argumentsToCommand, execMode), execMode);
            } else {
                OutStream.outputIntoCLI(correctnessStatus.getMessage(), execMode);
            }
        } else {
            return DataInOutStatus.NOCOMMAND;
        }
        return DataInOutStatus.SUCCESFULLY;
    }
}
