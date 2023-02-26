package client.streams.out;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;

import client.streams.DataInOutStatus;
import server.data.classes.City;
import server.data.classes.CollectionWorker;

public class OutFileStream {
    public DataInOutStatus openOutputStream(CollectionWorker worker, LinkedHashMap<String, String> fields) {
        String fileName = System.getenv().get("FILE_NAME");
        try {
            FileOutputStream outputFileStream = new FileOutputStream(fileName);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputFileStream, StandardCharsets.UTF_8);           
            outputStreamWriter.write("key");
            for (String objkey : fields.keySet()) {
                outputStreamWriter.write("," + objkey);
            }
            outputStreamWriter.write("\n");
            LinkedHashMap<String, City> mainCollection = worker.getMainCollection();
            System.out.println(mainCollection.toString());
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
