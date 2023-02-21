package src.streams;

import java.util.LinkedHashMap;

import src.commands.classes.CommandController;
import src.data.classes.CollectionWorker;

public interface StreamOpenerInterface {
    public void openStream (CommandController commandController, CollectionWorker dataWorker, LinkedHashMap<String, String> fields);
}
