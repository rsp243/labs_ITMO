package src.streams;

import src.commands.classes.CollectionWorker;
import src.commands.classes.CommandController;
import src.streams.in.InCLIstream;
import src.streams.out.OutCLIstream;

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
                InCLIstream inputCLIStream = new InCLIstream();
                inputCLIStream.openInputStream(outputCLIStream, commandController, dataWorker);
                break;
            }
            default: System.out.print("Invalid Input Stream Type");
        }
    }
}
