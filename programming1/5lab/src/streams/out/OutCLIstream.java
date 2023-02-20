package src.streams.out;

import java.util.ArrayList;

import src.commands.classes.CommandController;
import src.data.classes.CollectionWorker;
import src.streams.DataInOutStatus;

public class OutCLIstream implements OutputStreamsOpening {
    public OutCLIstream () {}

    @Override
    public DataInOutStatus openOutputStream(CommandController commandController, CollectionWorker dataWorker, String commandName, ArrayList<String> extraArguments) {
        this.outputIntoCLI(commandController.execute(dataWorker, commandName, extraArguments));
        return DataInOutStatus.SUCCESFULLY;
    }

    public DataInOutStatus outputIntoCLI(String strToCLI) {
        System.out.print(strToCLI);
        return DataInOutStatus.SUCCESFULLY;
    }
}
