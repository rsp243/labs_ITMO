package src.client.streams;

import java.util.LinkedHashMap;

import src.server.commands.classes.CommandController;
import src.server.data.classes.CollectionWorker;

public interface StreamOpenerInterface {
    public void openStream (CommandController commandController, CollectionWorker dataWorker, LinkedHashMap<String, String> fields);
}
