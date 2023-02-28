package server.commands.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import server.data.classes.CollectionWorker;

public class CommandController {
    private static HashMap<String, Command> mapOfCommands;
    private static CollectionWorker worker;

    public CommandController(CollectionWorker aWorker) {
        worker = aWorker;
        mapOfCommands = new HashMap<>();
        Help helpCommand = new Help(mapOfCommands.values());
        mapOfCommands.put(helpCommand.getName(), helpCommand);
        Info infoCommand = new Info();
        mapOfCommands.put(infoCommand.getName(), infoCommand);
        Insert insertCommand = new Insert();
        mapOfCommands.put(insertCommand.getName(), insertCommand);
        insertCommand.setUniqueID(worker.getUniqueID());
        Exit exitCommand = new Exit();
        mapOfCommands.put(exitCommand.getName(), exitCommand);
        Show showCommand = new Show();
        mapOfCommands.put(showCommand.getName(), showCommand);
        RemoveKey removeCommand = new RemoveKey();
        mapOfCommands.put(removeCommand.getName(), removeCommand);
        Update updateCommand = new Update();
        mapOfCommands.put(updateCommand.getName(), updateCommand);
        Save saveCommand = new Save();
        mapOfCommands.put(saveCommand.getName(), saveCommand);
        SumOfCarCodeCommand sumOfCarCodeCommand = new SumOfCarCodeCommand();
        mapOfCommands.put(sumOfCarCodeCommand.getName(), sumOfCarCodeCommand);
        CountByClimate countByClimate = new CountByClimate();
        mapOfCommands.put(countByClimate.getName(), countByClimate);
        RemoveGreaterKey removeGreaterKey = new RemoveGreaterKey();
        mapOfCommands.put(removeGreaterKey.getName(), removeGreaterKey); 
        Clear clear = new Clear();
        mapOfCommands.put(clear.getName(), clear); 
        RemoveGreater removeGreater = new RemoveGreater();
        mapOfCommands.put(removeGreater.getName(), removeGreater);
        ReplaceIfGreater replaceIfGreater = new ReplaceIfGreater();
        mapOfCommands.put(replaceIfGreater.getName(), replaceIfGreater);
    }

    public static Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }

    public CollectionWorker getWorker() {
        return worker;
    }

    public static String execute(Command commandObj, ArrayList<String> extraArguments) {
        StringBuilder execution = new StringBuilder();
        execution.append(commandObj.execute(worker, extraArguments)); // Adding into String Builder argument.execute()
        return execution.toString();
    }
}
