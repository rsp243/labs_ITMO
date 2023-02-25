package src.client;

import java.util.Map;

import src.server.commands.classes.Command;
import src.server.commands.classes.CommandController;

public class MetaInfoCommand {
    private Map<String, Command> mapOfCommands;

    public MetaInfoCommand() {
        mapOfCommands = CommandController.getMapOfCommands();
    }

    public Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }
}
