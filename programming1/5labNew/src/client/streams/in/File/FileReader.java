package client.streams.in.File;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import client.streams.out.OutCLIstream;

public class FileReader {
    public ArrayList<String> readFile(String filename) {
        ArrayList<String> linesArrayList = new ArrayList<>();   
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                linesArrayList.add(line);
            }
            sc.close();
            return linesArrayList;
        } catch (IOException | NullPointerException e) {
            OutCLIstream.outputIntoCLI("Error with file, check path of the file.");
            return new ArrayList<>();
        }
    }
}
