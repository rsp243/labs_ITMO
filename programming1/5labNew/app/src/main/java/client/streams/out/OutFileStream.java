package client.streams.out;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import client.streams.DataInOutStatus;
import server.data.classes.City;
import server.data.classes.Receiver;

/**
 * OutFileStream Class
 * Output objects from main collectrion into file from environment variable 'FILE_NAME' in csv format 
 */

public class OutFileStream {
    public DataInOutStatus openOutputStream(Receiver worker, LinkedHashMap<String, String> fields) {
        String fileName = System.getenv().get("FILE_NAME");
        System.out.println("Output data into file: " + fileName);
        String filepath = "app/src/main/java/server/data/file/" + fileName; 
        try {
            FileOutputStream outputFileStream = new FileOutputStream(filepath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputFileStream, StandardCharsets.UTF_8);           
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
        } catch (IOException | NullPointerException e) {
            return DataInOutStatus.FAILED;
        }
        return DataInOutStatus.SUCCESFULLY;
    }
}
