package client.streams.in;

import server.data.classes.City;
import server.data.classes.Factories.CityFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

public class ReaderCSV {
    public LinkedHashMap<String, City> getSavedCollection() {
        LinkedHashMap<String, City> savedCollection = new LinkedHashMap<>();
        String fileName = System.getenv().get("FILE_NAME");
        // try {
        //     FileReader fr = new FileReader(fileName);
        //     CSVReader reader = new CSVReaderBuilder(fr).build();
        //     List<String[]> myEntries = reader.readAll();
        //     for (int iter = 1; iter < myEntries.size(); iter++) {
        //         ArrayList<String> arrayListArgs = new ArrayList<String>();
        //         if (iter != 0) {
        //             City cityObj = new CityFactory().createCity(arrayListArgs);
        //             System.out.println(cityObj.toString());
        //         }
        //     }
        // } catch (IOException | CsvException e) {
        //     System.out.println("file not found");
        // } 
        return savedCollection;
    }
}
