package src.commands.classes;

import java.util.ArrayList;

import src.data.classes.CollectionWorker;

public class Exit extends Command {
    public Exit() {
        super("exit", "End the program execution (without saving data into the storage)");
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        return "The program execution has ended.";
    }
}
