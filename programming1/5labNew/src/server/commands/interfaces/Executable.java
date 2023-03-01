package server.commands.interfaces;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.classes.CollectionWorker;

public interface Executable {
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments, ExecutionMode execMode);
}
