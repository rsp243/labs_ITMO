package src.streams.in;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;
import src.streams.DataInOutStatus;
import src.streams.out.OutCLIstream;

public interface InputStreamsOpening {
    public DataInOutStatus openCLIStream(OutCLIstream outputStream, CommandController commandController, CollectionWorker dataWorker);
}
