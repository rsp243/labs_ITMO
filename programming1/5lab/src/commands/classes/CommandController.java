package src.commands.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.commands.interfaces.Executable;
import src.data.classes.CollectionWorker;

public class CommandController implements Executable {
    private HashMap<String, Command> mapOfCommands;

    public CommandController() {
        mapOfCommands = new HashMap<>();
        Help helpCommand = new Help(mapOfCommands.values());
        mapOfCommands.put(helpCommand.getName(), helpCommand);
        Info infoCommand = new Info();
        mapOfCommands.put(infoCommand.getName(), infoCommand);
        Insert insertCommand = new Insert();
        mapOfCommands.put(insertCommand.getName(), insertCommand);
        Exit exitCommand = new Exit();
        mapOfCommands.put(exitCommand.getName(), exitCommand);
        Show showCommand = new Show();
        mapOfCommands.put(showCommand.getName(), showCommand);
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
