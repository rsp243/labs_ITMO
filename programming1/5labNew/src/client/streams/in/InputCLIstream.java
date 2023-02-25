package client.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import client.streams.DataInOutStatus;
import client.streams.out.OutCLIstream;

public class InputCLIstream {
    private static BufferedReader inpReader = new BufferedReader(new InputStreamReader(System.in));

    public static BufferedReader getInpReader() {
        return inpReader;
    }

    public static DataInOutStatus openCLIStream() {
        try {
            OutCLIstream.outputIntoCLI("Type commands below.");
            while (true) {
                String inputData = inpReader.readLine();
                if (inputData != null) {
                    if (!inputData.equals("exit")) {
                        inputData = inputData.trim();
                        DataInOutStatus checkedCommand = new CommandParser().parse(inputData);
                        if (checkedCommand != DataInOutStatus.SUCCESFULLY) {
                            OutCLIstream.outputIntoCLI(checkedCommand.getMessage());
                        }
                    } else {
                        inpReader.close();
                    }
                }
            }
        } catch (IOException e) {
            return DataInOutStatus.FAILED;
        }
    }
}
