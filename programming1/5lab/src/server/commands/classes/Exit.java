package src.server.commands.classes;

import java.util.ArrayList;

import src.server.data.classes.CollectionWorker;

public class Exit extends Command {
    public Exit() {
        super("exit", "End the program execution (without saving data into the storage)", 0, CommandType.STATE_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        System.out.println("The program execution has ended.");
        System.exit(0);
        return "";
    }
}
