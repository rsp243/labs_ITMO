package client.streams.out;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import client.streams.DataInOutStatus;
import client.streams.in.ExecutionMode;
import server.data.City;
import server.data.Receiver;

/**
 * OutFileStream Class
 * Output objects from main collectrion into file from environment variable
 * 'FILE_NAME' in csv format
 */

public class OutFileStream {
    public DataInOutStatus openOutputStream(Receiver worker, LinkedHashMap<String, String> fields) {
        String fileName = System.getenv().get("FILE_NAME");
        String filepath = "./" + fileName;
        if (fileName != null) {
            if (!fileName.contains(".csv")) {
                OutStream.outputIntoCLI("Your fileType wrong. Correct fileType: '.csv'", ExecutionMode.CLI);
                return DataInOutStatus.FAILED;
            } else {
                try {
                    FileOutputStream outputFileStream = new FileOutputStream(filepath);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputFileStream,
                            StandardCharsets.UTF_8);
                    outputStreamWriter.write("key");
                    for (String objkey : fields.keySet()) {
                        outputStreamWriter.write("," + objkey);
                    }
                    outputStreamWriter.write("\n");
                    LinkedHashMap<String, City> mainCollection = worker.getMainCollection();
                    for (String key : mainCollection.keySet()) {
                        City cityObj = mainCollection.get(key);
                        outputStreamWriter.write(key);
                        for (String cityFieldValue : cityObj.getAllFieldsValues()) {
                            outputStreamWriter.write("," + cityFieldValue);
                        }
                        outputStreamWriter.write("\n");
                    }
                    outputStreamWriter.close();
                    outputFileStream.close();
                } catch (IOException e) {
                    return DataInOutStatus.FAILED;
                }
            }
        } else {
            OutStream.outputIntoCLI("Add into a enviroment variable 'FILE_NAME' the value - name of your file.",
                    ExecutionMode.CLI);
            return DataInOutStatus.FAILED;
        }
        return DataInOutStatus.SUCCESFULLY;
    }
}
