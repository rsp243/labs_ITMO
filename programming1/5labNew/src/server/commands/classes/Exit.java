package server.commands.classes;

import java.util.ArrayList;

import server.data.classes.CollectionWorker;

public class Exit extends Command {
    public Exit() {
        super("exit", "End the program execution (without saving data into the storage)", 0, CommandType.STATE_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments) {
        return "";
    }
}
