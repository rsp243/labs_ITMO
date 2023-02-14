package src.streams;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;

public interface StreamOpenerInterface {
    public void openStream (CommandController commandController, CollectionWorker dataWorker, String outputData);
}
