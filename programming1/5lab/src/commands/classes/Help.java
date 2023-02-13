package src.commands.classes;

import java.util.ArrayList;
import java.util.Collection;

public class Help extends Command {
    Collection<Command> collectionOfCommands;
    
    public Help(Collection<Command> aCollectionOfCommands) {
        super("help", "Output info about all commands");
        collectionOfCommands = aCollectionOfCommands;
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> arguments) {
        return collectionOfCommands.toString();
    }
}
