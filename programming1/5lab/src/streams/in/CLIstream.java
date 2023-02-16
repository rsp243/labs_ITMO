package src.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import src.commands.classes.Command;
import src.commands.classes.CommandController;
import src.data.classes.CollectionWorker;
import src.streams.DataInOutStatus;
import src.streams.out.OutCLIstream;

public class CLIstream implements InputStreamsOpening {
    public CLIstream() {
    }

    @Override
    public DataInOutStatus openCLIStream(OutCLIstream outputStream, CommandController commandController,
            CollectionWorker dataWorker) {
        BufferedReader inpReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String inputData = inpReader.readLine();
                if (inputData != null) {
                    inputData = inputData.trim();
                    DataInOutStatus dataFetchController = DataInOutStatus.SUCCESFULLY;
                    String commandName = inputData.split(" ")[0];
                    ArrayList<String> extraArguments = new ArrayList<String>();
                    if (commandController.getMapOfCommands().containsKey(commandName)) {
                        Command commandObj = commandController.getMapOfCommands().get(commandName);
                        if (commandObj.getCountOfExtraArgs() >= 1) {
                            if (inputData.split(" ").length < 2) {
                                dataFetchController = DataInOutStatus.FAILED;
                            } else {
                                String key = inputData.split(" ")[1];
                                extraArguments.add(key);
                                if (commandObj.getCountOfExtraArgs() > 1) {
                                    System.out.println("Type extra data of object.");
                                    for (Integer iter = 0; iter < commandObj.getCountOfExtraArgs(); iter++) {
                                        String extraInputData = inpReader.readLine();
                                        if (extraInputData != null) {
                                            extraArguments.add(extraInputData.trim());
                                        }
                                    }
                                }
                                if (extraArguments.size() != commandObj.getCountOfExtraArgs()) {
                                    dataFetchController = DataInOutStatus.FAILED;
                                }
                            }
                        }
                    }
                    if (dataFetchController == DataInOutStatus.FAILED) {
                        System.out.println("You have typed wrong arguments to last command. Try adain.");
                    } else {
                        outputStream.openOutputStream(commandController, dataWorker, commandName, extraArguments);
                    }
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
