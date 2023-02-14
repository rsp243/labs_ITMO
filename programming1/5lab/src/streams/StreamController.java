package src.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
                            System.out.print(commandController.execute(dataWorker, commandName, extraArguments));
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
            case OUTPUT_CLI: {

                break;
            }

            default: System.out.print("Invalid Stream Type");
        }
    }
}
