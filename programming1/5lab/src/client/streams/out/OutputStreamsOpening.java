package src.client.streams.out;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import src.client.streams.DataInOutStatus;
import src.server.commands.classes.CommandController;
import src.server.data.classes.CollectionWorker;

public interface OutputStreamsOpening {
    public DataInOutStatus openOutputStream(CommandController commandController, LinkedHashMap<String, String> fields, String commandName, ArrayList<String> extraArguments);
    public DataInOutStatus outputIntoCLI(String strToCLI);
}
