package src.client.streams.out;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import src.client.streams.DataInOutStatus;
import src.server.commands.classes.CommandController;
import src.server.data.classes.CollectionWorker;

public class OutFileStream implements OutputStreamsOpening {
    @Override
    public DataInOutStatus openOutputStream(CommandController commandController, LinkedHashMap<String, String> fields,
            String commandName, ArrayList<String> extraArguments) {
        String fileName = System.getenv().get("FILE_NAME");
        try {
            FileOutputStream outputFileStream = new FileOutputStream(fileName);
            DataOutputStream outputDataStream = new DataOutputStream(outputFileStream);
            outputDataStream.writeChars("key;");
            for (String objkey : fields.keySet()) {
                outputDataStream.writeChars(objkey + ";");
            }
            outputDataStream.writeChars("\n");
            outputDataStream.close();
            outputFileStream.close();
        } catch (IOException | NullPointerException e) {
            return DataInOutStatus.FAILED;
        }
        return DataInOutStatus.SUCCESFULLY;
    }

    @Override
    public DataInOutStatus outputIntoCLI(String strToCLI) {
        return DataInOutStatus.FAILED;
    }
}
