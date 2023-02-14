package src.streams;

import java.util.ArrayList;

public class StreamController implements StreamOpenerInterface{
    private StreamType streamType;
    
    public StreamController(StreamType aStreamType) {
        streamType = aStreamType;
    }

    public StreamType getStreamType() {
        return streamType;
    }

    @Override
    public void openStream(ArrayList<String> data) {
        switch (streamType) {
            case INPUT_CLI: {
                
                break;
            }
            case OUTPUT_CLI: {

                break;
            }

            default: System.out.print("Invalid Stream Type");
        }
    }
}
