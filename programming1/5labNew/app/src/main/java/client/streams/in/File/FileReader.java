package client.streams.in.File;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import client.streams.in.ExecutionMode;
import client.streams.out.OutStream;

public class FileReader {
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> linesArrayList = new ArrayList<>();
        String filepath = "app/src/main/java/server/data/file/" + fileName; 
        OutStream.outputIntoCLI("Name of file: '" + fileName + "'.\nFilePath: '" + filepath + "'.", ExecutionMode.CLI);
        try {
            Scanner sc = new Scanner(new File(filepath));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                linesArrayList.add(line);
            }
            sc.close();
            OutStream.outputIntoCLI("File was readed successfully.", ExecutionMode.CLI);
            return linesArrayList;
        } catch (IOException | NullPointerException e) {
            OutStream.outputIntoCLI("Error with file, check path of the file. Check file's format: '<filename>.<FileFormat>'.\nWe have searched this file in directory: 'app/src/main/java/server/data/file/'.", ExecutionMode.CLI);
            return new ArrayList<>();
        }
    }
}
