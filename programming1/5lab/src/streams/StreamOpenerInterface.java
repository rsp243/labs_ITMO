package src.streams;

import java.util.HashMap;

import src.commands.classes.CommandController;
import src.data.classes.CollectionWorker;

public interface StreamOpenerInterface {
    public void openStream (CommandController commandController, CollectionWorker dataWorker, HashMap<String, String> fields);
}
