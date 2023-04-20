package server.commands;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.Receiver;

public interface Executable {
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode);
}
