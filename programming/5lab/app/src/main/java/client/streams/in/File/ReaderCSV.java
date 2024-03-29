package client.streams.in.File;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

import com.opencsv.CSVParser;

import client.streams.in.ExecutionMode;
import client.streams.out.OutStream;
import server.data.City;
import server.data.Factories.CityFactory;
import server.data.Validators.CityValidator.CityValidator;
import server.fillers.Increment;

/**
 * ReaderCSV Class
 * Read file in CSV format
 */

public class ReaderCSV {
    private Increment uniqueID;

    /**
     * Read file in CSV format with Scanner and CSVParser from lib opencsv
     * 
     * @return Saved Main collection
     */
    public LinkedHashMap<String, City> getSavedCollection() {
        LinkedHashMap<String, City> savedCollection = new LinkedHashMap<>();
        String fileName = System.getenv().get("FILE_NAME");
        ArrayList<String> linesArrayList = new FileReader().readFile(fileName);
        CSVParser csvParser = new CSVParser();
        Integer iterator = 0;
        ArrayList<Long> idArray = new ArrayList<>();
        idArray.add((long) 1);
        try {
            for (String line : linesArrayList) {
                ArrayList<String> arrayListArgs = new ArrayList<String>();
                String[] parsedLine = csvParser.parseLine(line);
                for (String str : parsedLine) {
                    str = str.trim();
                    arrayListArgs.add(str);
                }
                String key = arrayListArgs.remove(0);
                String id = arrayListArgs.remove(0);
                String creationDate = arrayListArgs.remove(3);
                if (iterator != 0) {
                    if (idArray.contains(Long.parseLong(id))) {
                        OutStream.outputIntoCLI(
                                "Error with id! Check correctness your file's data. Check line:" + iterator,
                                ExecutionMode.CLI);
                    } else {
                        String birthday = arrayListArgs.remove(arrayListArgs.size() - 1);
                        String[] arrayBirthday = birthday.split("-");
                        birthday = arrayBirthday[2] + "." + arrayBirthday[1] + "." + arrayBirthday[0];
                        arrayListArgs.add(birthday);
                        CityValidator cityValidator = new CityValidator();
                        try {
                            if (cityValidator.validate(arrayListArgs)) {
                                City cityObj = new CityFactory().createCity((long) 0, arrayListArgs);
                                cityObj.setId(Long.parseLong(id));
                                idArray.add(Long.parseLong(id));
                                try {
                                    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",
                                            Locale.ENGLISH);
                                    Date date = parser.parse(creationDate);
                                    new Date();
                                    cityObj.setCreationDate(date);
                                    savedCollection.put(key, cityObj);
                                } catch (ParseException e) {
                                    OutStream.outputIntoCLI(
                                            "Error with parsing creation date! Check correctness of your data. Check line:"
                                                    + iterator,
                                            ExecutionMode.CLI);
                                }
                            }
                        } catch (IndexOutOfBoundsException | DateTimeException | IllegalArgumentException m) {
                            OutStream.outputIntoCLI(
                                "Error with dowloaded data! Check correctness of your data. Check line: "
                                        + iterator,
                                ExecutionMode.CLI);
                        }
                    }
                }
                iterator++;
            }
            uniqueID = new Increment(Collections.max(idArray));
            uniqueID.getNewId();
        } catch (IOException e) {
            OutStream.outputIntoCLI("Error with CSV file.", ExecutionMode.CLI);
        }
        return savedCollection;
    }

    public Increment getUniqueID() {
        return uniqueID;
    }
}
