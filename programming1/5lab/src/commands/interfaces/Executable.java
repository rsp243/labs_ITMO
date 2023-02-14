package src.commands.interfaces;

import java.util.ArrayList;

import src.commands.classes.CollectionWorker;

public interface Executable {
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments);
}
