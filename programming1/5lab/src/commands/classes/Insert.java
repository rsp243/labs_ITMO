package src.commands.classes;

import java.util.ArrayList;

import src.data.classes.CollectionWorker;

public class Insert extends Command {
    public Insert() {
        super("insert", "Add an element with typed key into the main collection");
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        
        return "Successfully";
    }
}
