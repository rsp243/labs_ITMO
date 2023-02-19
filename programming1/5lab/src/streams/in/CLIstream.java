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
                    String[] splittedInputData = inputData.split(" ");
                    String commandName = splittedInputData[0];
                    CommandDataChecker commandChecker = new CommandDataChecker();
                    if (commandChecker.checkInputedCommand(commandController, inputData,
                            inpReader) == DataInOutStatus.FAILED) {
                        System.out.println("You have typed wrong arguments to last command. Try adain.");
                    } else {
                        ArrayList<String> extraArguments = commandChecker.getExtraArguments();
                        if (commandController.getMapOfCommands().containsKey(commandName)) {
                            Command commandObj = commandController.getMapOfCommands().get(commandName);
                            if (commandObj.getCountOfExtraArgs() >= 1) {
                                if (splittedInputData.length == 2) {
                                    String key = inputData.split(" ")[1];
                                    extraArguments.add(0, key);
                                }
                            }
                        }
                        outputStream.openOutputStream(commandController, dataWorker, commandName,
                                extraArguments);
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
