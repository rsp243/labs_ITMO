package server.commands;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.classes.Receiver;


/**
 * Info command
 * Output info about main collection
 */

public class Info extends Command {
    public Info() {
        super("info", "Output info about main collection", 0, CommandType.INFO_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
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
