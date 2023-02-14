package src.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;

public class StreamController implements StreamOpenerInterface{
    private StreamType streamType;
    
    public StreamController(StreamType aStreamType) {
        streamType = aStreamType;
    }

    public StreamType getStreamType() {
        return streamType;
    }

    @Override
    public void openStream(CommandController commandController, CollectionWorker dataWorker) {
        switch (streamType) {
            case INPUT_CLI: {
                BufferedReader inpReader = new BufferedReader(new InputStreamReader(System.in));
                String inputData;
                try {
                    inputData = inpReader.readLine();
                    while (inputData != "exit\n") {
                        String commandName = inputData.split(" ")[0];
                        ArrayList<String> extraArguments = new ArrayList<String>();
                        for (String extraData : inputData.substring(commandName.length()).split(" ")) {
                            extraArguments.add(extraData);
                        };
                        System.out.print(commandController.execute(dataWorker, commandName, extraArguments));
                        inputData = inpReader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case OUTPUT_CLI: {

                break;
            }

            default: System.out.print("Invalid Stream Type");
        }
    }
}
