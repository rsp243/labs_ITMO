package client.streams.in;

import server.data.classes.City;

import com.opencsv.CSVParserBuilder;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CSVReader {
    public LinkedHashMap<String, City> getSavedCollection() {
        LinkedHashMap<String, City> savedCollection = new LinkedHashMap<>();
        String fileName = System.getenv().get("FILE_NAME");
        try {
            CSVReader reader = new FileReader("yourfile.csv").withCSVParser(new CSVParserBuilder()
                    .withSeparator('\t')
                    .build())
            .build();
        } catch (NullPointerException e) {
            System.out.println("Gay?");
        }
        // Map<String, String> values = new CSVReaderHeaderAware(new Scanner("yourfile.csv")).readMap();
        return savedCollection;
    }
}
