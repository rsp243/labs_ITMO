package src.server.commands.classes;

import java.util.ArrayList;

import src.server.data.classes.CollectionWorker;

public class Show extends Command {
    public Show() {
        super("show", "Output all elements from collection", 0, CommandType.INFO_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        StringBuilder strShow = new StringBuilder();
        if (worker.getMainCollection().size() == 0) {
            return "There is no elements in main collection.";
        }
        for (String iter : worker.getMainCollection().keySet()) {
            strShow.append("key = " + iter + ": " + worker.getMainCollection().get(iter).toString() + "\n");
        }
        return strShow.delete(strShow.toString().length() - 1, strShow.toString().length()).toString();
    }

}
