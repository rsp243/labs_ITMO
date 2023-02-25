package src.client.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import src.client.CommandParser;
import src.client.streams.DataInOutStatus;
import src.client.streams.out.OutCLIstream;
import src.server.commands.classes.CommandController;

public class InputCLIstream implements InputStreamsOpening {
    public InputCLIstream() {
    }

    @Override
    public DataInOutStatus openCLIStream() {
        BufferedReader inpReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String inputData = inpReader.readLine();
                if (inputData.equals("exit")) {
                    inputData = inputData.trim();
                    new CommandParser().parse(inputData);
                } else {
                    inpReader.close();
                    return DataInOutStatus.SUCCESFULLY;
                }
            }
        } catch (IOException e) {
            return DataInOutStatus.FAILED;
        }
    }
}
