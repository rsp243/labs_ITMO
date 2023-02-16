package src.streams;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;
import src.streams.in.CLIstream;
import src.streams.out.OutCLIstream;

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

    // Do split to input streams and output streams to current dirrectories
    @Override
    public void openStream(CommandController commandController, CollectionWorker dataWorker) {
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
                CLIstream inputCLIStream = new CLIstream();
                inputCLIStream.openCLIStream(outputCLIStream, commandController, dataWorker);
                break;
            }
            default: System.out.print("Invalid Input Stream Type");
        }
    }
}
