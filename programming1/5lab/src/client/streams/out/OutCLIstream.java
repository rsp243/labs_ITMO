package src.client.streams.out;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import src.client.streams.DataInOutStatus;
import src.server.commands.classes.CommandController;
import src.server.data.classes.CollectionWorker;

public class OutCLIstream implements OutputStreamsOpening {
    public OutCLIstream () {}

    @Override
    public DataInOutStatus openOutputStream(CommandController commandController, LinkedHashMap<String, String> fields, String commandName, ArrayList<String> extraArguments) {
        this.outputIntoCLI(commandController.execute(dataWorker, commandName, extraArguments));
        return DataInOutStatus.SUCCESFULLY;
    }

    public DataInOutStatus outputIntoCLI(String strToCLI) {
        System.out.print(strToCLI);
        return DataInOutStatus.SUCCESFULLY;
    }
}
