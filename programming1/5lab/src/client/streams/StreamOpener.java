package src.client.streams;

import java.util.HashMap;
import java.util.LinkedHashMap;

import src.client.streams.in.InputCLIstream;
import src.client.streams.out.OutCLIstream;
import src.server.commands.classes.CommandController;
import src.server.data.classes.CollectionWorker;

public class StreamOpener implements StreamOpenerInterface{
    private StreamType inputStreamType;
    private StreamType outputStreamType;
    
    public StreamOpener(StreamType anInputStreamType, StreamType anOutputStreamType) {
        inputStreamType = anInputStreamType;
        outputStreamType = anOutputStreamType;
    }

    public StreamType getInputStreamType() {
        return inputStreamType;
    }

    public StreamType getOutputStreamType() {
        return outputStreamType;
    }

    // Do split to input streams and output streams to current directories
    @Override
    public void openStream(CommandController commandController, CollectionWorker dataWorker, LinkedHashMap<String, String> fields) {
        OutCLIstream outputCLIStream = new OutCLIstream();
        switch (outputStreamType) {
            case OUTPUT_CLI: {
                outputCLIStream = new OutCLIstream();
                break;
            }
            default: System.out.print("Invalid Input Stream Type");
        }
        switch (inputStreamType) {
            case INPUT_CLI: {
                // Creating object of CLIstream and taking data from it
                InputCLIstream inputCLIStream = new InputCLIstream();
                inputCLIStream.openCLIStream(outputCLIStream, commandController, dataWorker, fields);
                break;
            }
            default: System.out.print("Invalid Input Stream Type");
        }
    }
}
