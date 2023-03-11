package server.commands.classes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import client.streams.in.ExecutionMode;
import server.data.classes.Receiver;

public class CommandController {
    private static LinkedHashMap<String, Command> mapOfCommands;
    private static Receiver worker;

    public CommandController(Receiver aWorker) {
        worker = aWorker;
        mapOfCommands = new LinkedHashMap<>();
        Help helpCommand = new Help(mapOfCommands.values());
        mapOfCommands.put(helpCommand.getName(), helpCommand);
        Info infoCommand = new Info();
        mapOfCommands.put(infoCommand.getName(), infoCommand);
        Show showCommand = new Show();
        mapOfCommands.put(showCommand.getName(), showCommand);
        Insert insertCommand = new Insert();
        mapOfCommands.put(insertCommand.getName(), insertCommand);
        insertCommand.setUniqueID(worker.getUniqueID());
        Update updateCommand = new Update();
        mapOfCommands.put(updateCommand.getName(), updateCommand);
        RemoveKey removeCommand = new RemoveKey();
        mapOfCommands.put(removeCommand.getName(), removeCommand);
        Clear clear = new Clear();
        mapOfCommands.put(clear.getName(), clear); 
        Save saveCommand = new Save();
        mapOfCommands.put(saveCommand.getName(), saveCommand);
        ExecuteScript executeScriptCommand = new ExecuteScript();
        mapOfCommands.put(executeScriptCommand.getName(), executeScriptCommand);
        Exit exitCommand = new Exit();
        mapOfCommands.put(exitCommand.getName(), exitCommand);
        RemoveGreater removeGreater = new RemoveGreater();
        mapOfCommands.put(removeGreater.getName(), removeGreater);
        ReplaceIfGreater replaceIfGreater = new ReplaceIfGreater();
        mapOfCommands.put(replaceIfGreater.getName(), replaceIfGreater);
        RemoveGreaterKey removeGreaterKey = new RemoveGreaterKey();
        mapOfCommands.put(removeGreaterKey.getName(), removeGreaterKey); 
        SumOfCarCodeCommand sumOfCarCodeCommand = new SumOfCarCodeCommand();
        mapOfCommands.put(sumOfCarCodeCommand.getName(), sumOfCarCodeCommand);
        CountByClimate countByClimate = new CountByClimate();
        mapOfCommands.put(countByClimate.getName(), countByClimate);
        PrintFieldDescendingMetersAboveSeaLevel printFieldDescendingMetersAboveSeaLevel = new PrintFieldDescendingMetersAboveSeaLevel();
        mapOfCommands.put(printFieldDescendingMetersAboveSeaLevel.getName(), printFieldDescendingMetersAboveSeaLevel);
    }

    public static Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }

    public Receiver getWorker() {
        return worker;
    }

    public static String execute(Command commandObj, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        execution.append(commandObj.execute(worker, extraArguments, execMode)); // Adding into String Builder argument.execute()
        return execution.toString();
    }
}
