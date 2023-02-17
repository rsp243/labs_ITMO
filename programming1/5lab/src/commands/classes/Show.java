package src.commands.classes;

import java.util.ArrayList;

import src.data.classes.CollectionWorker;

public class Show extends Command {
    public Show() {
        super("show", "Output all elements from collection", 0, CommandType.INFO_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        if (worker.getMainCollection().size() == 0) {
            return "There is no cities in main collection.";
        }
        return worker.getMainCollection().toString();
    }

}
