package src.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;

public class StreamController implements StreamOpenerInterface{
    private StreamType inputStreamType;
    private StreamType outputStreamType;
    
    public StreamController(StreamType anInputStreamType, StreamType anOutputStreamType) {
        inputStreamType = anInputStreamType;
        outputStreamType = anOutputStreamType;
    }

    public StreamType getInputStreamType() {
        return inputStreamType;
    }

    public StreamType getOutputStreamType() {
        return outputStreamType;
    }

    // Do split to input streams and output streams to current dirrectories
    @Override
    public void openStream(CommandController commandController, CollectionWorker dataWorker, String outputData) {
        switch (inputStreamType) {
            case INPUT_CLI: {
                BufferedReader inpReader = new BufferedReader(new InputStreamReader(System.in));
                StreamController outputDataStreamController = this;
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
                            outputDataStreamController.openStream(commandController, dataWorker, commandController.execute(dataWorker, commandName, extraArguments));
                        } else {
                            inpReader.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: System.out.print("Invalid Input Stream Type");
        }
        switch (outputStreamType) {
            case OUTPUT_CLI: {
                System.out.print(outputData);
                break;
            }
            default: System.out.print("Invalid Input Stream Type");
        }
    }
}
