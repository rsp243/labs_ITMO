package src.streams.out;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import src.commands.classes.CommandController;
import src.data.classes.CollectionWorker;
import src.streams.DataInOutStatus;

public class OutFileStream implements OutputStreamsOpening {
    @Override
    public DataInOutStatus openOutputStream(CommandController commandController, CollectionWorker dataWorker,
            String commandName, ArrayList<String> extraArguments) {
        String fileName = System.getenv().get("FILE_NAME");
        try {
            FileOutputStream outputFileStream = new FileOutputStream(fileName);
            DataOutputStream outputDataStream = new DataOutputStream(outputFileStream);
            for (String objkey : dataWorker.getMainCollection().keySet()) {
                outputDataStream.writeChars(objkey + ";");
            }
            outputDataStream.writeChars("Hello!;"); // Testing correct filepath format and writing there a word :-)
            outputDataStream.close();
            outputFileStream.close();
        } catch (IOException e) {
            return DataInOutStatus.FAILED;
        }
        return DataInOutStatus.SUCCESFULLY;
    }

    @Override
    public DataInOutStatus outputIntoCLI(String strToCLI) {
        return DataInOutStatus.FAILED;
    }
}
