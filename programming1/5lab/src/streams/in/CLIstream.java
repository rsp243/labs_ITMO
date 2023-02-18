package src.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.print.attribute.Size2DSyntax;

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
                    String[] splittedInputData = inputData.split(" ");
                    String commandName = splittedInputData[0];
                    ArrayList<String> extraArguments = new ArrayList<String>();
                    if (commandController.getMapOfCommands().containsKey(commandName)) {
                        Command commandObj = commandController.getMapOfCommands().get(commandName);
                        if (commandObj.getCountOfExtraArgs() >= 1) {
                            if (splittedInputData.length == 2) {
                                String key = inputData.split(" ")[1];
                                extraArguments = new ObjReading().objRead(commandController, commandName, inpReader,
                                        dataFetchController, inputData);
                                if (extraArguments.size() == 0 && commandObj.getCountOfExtraArgs() > 1) {
                                    dataFetchController = DataInOutStatus.FAILED;
                                }
                                extraArguments.add(0, key);
                            } else {
                                dataFetchController = DataInOutStatus.FAILED;
                            }
                        }
                    }
                    if (dataFetchController == DataInOutStatus.FAILED) {
                        System.out.println("You have typed wrong arguments to last command. Try adain.");
                    } else {
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
