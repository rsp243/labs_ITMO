package src.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;
import src.streams.DataInOutStatus;
import src.streams.out.OutCLIstream;

public class InCLIstream implements InputStreamsOpening {
    public InCLIstream () {}

    @Override
    public DataInOutStatus openInputStream(OutCLIstream outputStream, CommandController commandController, CollectionWorker dataWorker) {
        BufferedReader inpReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String inputData = inpReader.readLine();
                if (inputData != null) {
                    inputData = inputData.trim();
                    String commandName = inputData.split(" ")[0];
                    ArrayList<String> extraArguments = new ArrayList<String>();
                    for (String extraData : inputData.substring(commandName.length()).split(" ")) {
                        extraArguments.add(extraData);
                    };
                    outputStream.openOutputStream(commandController, dataWorker, commandName, extraArguments);
                } else {
                    inpReader.close();
                    return DataInOutStatus.SUCCESFULLY;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DataInOutStatus.FAILED;
    }
}
