package server.commands.interfaces;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.classes.Receiver;

public interface Executable {
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode);
}
