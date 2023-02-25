package src.server.commands.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

import src.client.streams.out.OutFileStream;
import src.server.data.classes.CollectionWorker;

public class Save extends Command {
    Collection<Command> collectionOfCommands;
    
    public Save(Collection<Command> aCollectionOfCommands) {
        super("save", "Saving main collection into file.", 0, CommandType.COLLECTION_WORKER);
        collectionOfCommands = aCollectionOfCommands;
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        String resultStr = "Successfully";
        // OutFileStream outToFile = new OutFileStream();
        // outToFile.openOutputStream(null, worker, new LinkedHashMap<String, String>(), null, null);
        return resultStr;
    }
}
