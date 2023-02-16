package src.commands.classes;

import java.util.ArrayList;

import src.data.classes.CollectionWorker;

public class Info extends Command {
    public Info() {
        super("info", "Output info about main collection", 0);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        StringBuilder execution = new StringBuilder();
        //Type / Class of main collection
        execution.append("Type = " + worker.getDataBase().getMainCollection().getClass() + "\n");
        //Size of main collection
        execution.append("Size = " + worker.getDataBase().getMainCollection().size() + "\n");
        //Date of Initialisation of main collection
        execution.append("Date of initialisation = " + worker.getDataBase().getDateOfInitialization() + "\n");
        //Date of last change of main collection
        execution.append("Date of last change = " + worker.getDataBase().getDateOfLastChange());
        return execution.toString();
    }
}
