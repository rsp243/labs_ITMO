package src.streams.in;

import java.util.HashMap;

import src.commands.classes.CommandController;
import src.data.classes.CollectionWorker;
import src.streams.DataInOutStatus;
import src.streams.out.OutCLIstream;

public interface InputStreamsOpening {
    public DataInOutStatus openCLIStream(OutCLIstream outputStream, CommandController commandController,
            CollectionWorker dataWorker, HashMap<String, String> fields);
}
