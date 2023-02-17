package src.streams.out;

import java.io.BufferedReader;
import java.util.ArrayList;

import src.commands.classes.CommandController;
import src.data.classes.CollectionWorker;
import src.streams.DataInOutStatus;

public interface OutputStreamsOpening {
    public DataInOutStatus openOutputStream(CommandController commandController, CollectionWorker dataWorker, String commandName, ArrayList<String> extraArguments);
}
