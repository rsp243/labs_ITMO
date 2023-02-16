package src.commands.classes;

import java.util.ArrayList;
import java.util.Collection;

import src.data.classes.CollectionWorker;

public class Help extends Command {
    Collection<Command> collectionOfCommands;
    
    public Help(Collection<Command> aCollectionOfCommands) {
        super("help", "Output info about all commands");
        collectionOfCommands = aCollectionOfCommands;
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        StringBuilder helpExcStr = new StringBuilder();
        for (Command command : collectionOfCommands) {
            helpExcStr.append(command.toString() + "\n");
        }
        helpExcStr.delete(helpExcStr.toString().length() - 2, helpExcStr.toString().length());
        return helpExcStr.toString();
    }
}
