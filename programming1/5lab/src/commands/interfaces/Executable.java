package src.commands.interfaces;

import java.util.ArrayList;

import src.data.classes.CollectionWorker;

public interface Executable {
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments);
}
