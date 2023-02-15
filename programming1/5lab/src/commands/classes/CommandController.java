package src.commands.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.commands.interfaces.Executable;

public class CommandController implements Executable {
    private HashMap<String, Command> mapOfCommands;

    public CommandController() {
        mapOfCommands = new HashMap<>();
        Help helpCommand = new Help(mapOfCommands.values());
        mapOfCommands.put("help", helpCommand);
        Info infoCommand = new Info();
        mapOfCommands.put("info", infoCommand);
        Exit exitCommand = new Exit();
        mapOfCommands.put("exit", exitCommand);
    }

    public Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        StringBuilder execution = new StringBuilder();
        if (mapOfCommands.containsKey(nameOfCommand)) {
            execution.append(mapOfCommands.get(nameOfCommand).execute(worker, nameOfCommand, extraArguments) + "\n");// Adding into String Builder argument.execute()
        } else {
            execution.append("There is no command with that name. Try again.\n"); // return "There is no comand with that name. Try again."
        }
        return execution.toString();
    }
}
