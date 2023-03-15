package server.commands;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.Receiver;

/**
 * Exit command
 * End the program execution (without saving data into the storage)
 */

public class Exit extends Command {
    public Exit() {
        super("exit", "End the program execution (without saving data into the storage)", 0, CommandType.STATE_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        return "";
    }
}
