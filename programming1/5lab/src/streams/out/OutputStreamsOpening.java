package src.streams.out;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import src.commands.classes.CommandController;
import src.data.classes.CollectionWorker;
import src.streams.DataInOutStatus;

public interface OutputStreamsOpening {
    public DataInOutStatus openOutputStream(CommandController commandController, CollectionWorker dataWorker, LinkedHashMap<String, String> fields, String commandName, ArrayList<String> extraArguments);
    public DataInOutStatus outputIntoCLI(String strToCLI);
}
