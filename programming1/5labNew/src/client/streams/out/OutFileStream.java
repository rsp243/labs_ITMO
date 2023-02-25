package client.streams.out;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import client.streams.DataInOutStatus;
import server.data.classes.City;
import server.data.classes.CollectionWorker;

public class OutFileStream {
    public DataInOutStatus openOutputStream(CollectionWorker worker, LinkedHashMap<String, String> fields) {
        String fileName = System.getenv().get("FILE_NAME");
        try {
            FileOutputStream outputFileStream = new FileOutputStream(fileName);
            DataOutputStream outputDataStream = new DataOutputStream(outputFileStream);
            DataOutputStream.nullOutputStream();
            outputDataStream.writeChars("key,");
            for (String objkey : fields.keySet()) {
                outputDataStream.writeChars(objkey + ",");
            }
            outputDataStream.writeChars("\n");
            LinkedHashMap<String, City> mainCollection = worker.getMainCollection();
            System.out.println(mainCollection.toString());
            for (String key : mainCollection.keySet()) {
                City cityObj = mainCollection.get(key);
                outputDataStream.writeChars(key + ",");
                for (String cityFieldValue : cityObj.getAllFieldsValues()) {
                    outputDataStream.writeChars(cityFieldValue + ",");
                }
                outputDataStream.writeChars("\n");
            }
            outputDataStream.close();
            outputFileStream.close();
        } catch (IOException | NullPointerException e) {
            return DataInOutStatus.FAILED;
        }
        return DataInOutStatus.SUCCESFULLY;
    }
}
