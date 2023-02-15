package src.streams.out;

import java.util.ArrayList;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;
import src.streams.DataInOutStatus;

public class OutCLIstream implements  OutputStreamsOpening {
    public OutCLIstream () {}

    @Override
    public DataInOutStatus openOutputStream(CommandController commandController, CollectionWorker dataWorker, String commandName, ArrayList<String> extraArguments) {
        System.out.print(commandController.execute(dataWorker, commandName, extraArguments));
        return DataInOutStatus.SUCCESFULLY;
    }
}
