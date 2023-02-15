package src.streams.out;

import java.util.ArrayList;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;
import src.streams.DataInOutStatus;

public interface OutputStreamsOpening {
    public DataInOutStatus openOutputStream(CommandController commandController, CollectionWorker dataWorker, String commandName, ArrayList<String> extraArguments);
}
