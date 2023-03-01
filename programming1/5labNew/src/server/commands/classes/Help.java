package server.commands.classes;

import java.util.ArrayList;
import java.util.Collection;

import client.streams.in.ExecutionMode;
import server.data.classes.CollectionWorker;

public class Help extends Command {
    Collection<Command> collectionOfCommands;
    
    public Help(Collection<Command> aCollectionOfCommands) {
        super("help", "Output info about all commands", 0, CommandType.INFO_WORKER);
        collectionOfCommands = aCollectionOfCommands;
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        StringBuilder execution = new StringBuilder();
        for (Command command : collectionOfCommands) {
            execution.append(command.toString() + "\n");
        }
        execution.delete(execution.toString().length() - 1, execution.toString().length());
        return execution.toString();
    }
}
