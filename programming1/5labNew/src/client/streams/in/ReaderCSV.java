package client.streams.in;

import server.data.classes.City;
import server.data.classes.Factories.CityFactory;
import server.data.classes.Validators.classes.CityValidator.CityValidator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

import com.opencsv.CSVParser;

import client.streams.out.OutCLIstream;

public class ReaderCSV {
    public LinkedHashMap<String, City> getSavedCollection() {
        LinkedHashMap<String, City> savedCollection = new LinkedHashMap<>();
        String fileName = System.getenv().get("FILE_NAME");
        try {
            Scanner sc = new Scanner(new File(fileName));
            Integer iterator = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();            
                CSVParser csvParser = new CSVParser();
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
                    String birthday = arrayListArgs.remove(arrayListArgs.size() - 1);
                    String[] arrayBirthday = birthday.split("-");
                    birthday = arrayBirthday[2] + "." + arrayBirthday[1] + "." + arrayBirthday[0];
                    arrayListArgs.add(birthday);
                    City cityObj = new CityFactory().createCity(arrayListArgs);
                    cityObj.setId(Long.parseLong(id));
                    try {
                        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                        Date date = parser.parse(creationDate);
                        new Date();
                        cityObj.setCreationDate(date);
                        savedCollection.put(key, cityObj);               
                    } catch (ParseException | DateTimeException e) {
                        OutCLIstream.outputIntoCLI("Error with parsing data! Check correctness of your data.");
                    }
                }
                iterator++;
            }
            sc.close();
        } catch (IOException e) {
            OutCLIstream.outputIntoCLI("Error with file");
        }
        return savedCollection;
    }
}