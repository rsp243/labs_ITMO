package server.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.MetaInfoCommand;
import client.streams.DataInOutStatus;
import client.streams.in.ExecutionMode;
import client.streams.out.OutFileStream;
import server.data.Receiver;

/**
 * Save command
 * Saving main collection into file.
 */

public class Save extends Command {    
    public Save() {
        super("save", "save", "Saving main collection into file.", 0, CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        String resultStr = "Successfully";
        OutFileStream outToFile = new OutFileStream();
        LinkedHashMap<String, String> fields = MetaInfoCommand.getFields();
        if (outToFile.openOutputStream(worker, fields) == DataInOutStatus.FAILED) {
            resultStr = "Failed. Try again.";
        };
        
        return resultStr;
    }
}
