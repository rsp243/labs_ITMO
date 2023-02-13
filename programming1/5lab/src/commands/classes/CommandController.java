package src.commands.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.commands.interfaces.Executable;

public class CommandController implements Executable {
    private HashMap<String, Command> mapOfCommands;

    public CommandController() {
        mapOfCommands = new HashMap<>();
        Info infoCommand = new Info();
        mapOfCommands.put("info", infoCommand);
        Help helpCommand = new Help(mapOfCommands.values());
        mapOfCommands.put("help", helpCommand);
    }

    public Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> arguments) {
        StringBuilder execution = new StringBuilder();
        for (String argument : arguments) {
            if (mapOfCommands.containsKey(argument)) {
                execution.append(mapOfCommands.get(argument).execute(worker, new ArrayList<String>()) + "\n");// Adding into String Builder argument.execute()
            } else {
                execution.append("There is no comand with that name. Try again.\n"); // return "There is no comand with that name. Try again."
            }
        }
        return execution.toString();
    }
}
