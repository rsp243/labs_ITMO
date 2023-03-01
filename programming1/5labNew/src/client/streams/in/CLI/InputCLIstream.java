package client.streams.in.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import client.streams.DataInOutStatus;
import client.streams.in.CommandParser;
import client.streams.in.ExecutionMode;
import client.streams.out.OutCLIstream;

public class InputCLIstream {
    private static BufferedReader inpReader = new BufferedReader(new InputStreamReader(System.in));

    public static BufferedReader getInpReader() {
        return inpReader;
    }

    public static DataInOutStatus openCLIStream(ExecutionMode execMode) {
        try {
            OutCLIstream.outputIntoCLI("Type commands below.", ExecutionMode.CLI);
            String inputData = inpReader.readLine();
            while (inputData != null) {
                if (inputData != null) {
                    if (!inputData.equals("exit")) {
                        inputData = inputData.trim();
                        DataInOutStatus checkedCommand = new CommandParser().parse(inputData, execMode);
                        if (checkedCommand != DataInOutStatus.SUCCESFULLY) {
                            OutCLIstream.outputIntoCLI(checkedCommand.getMessage(), ExecutionMode.CLI);
                        }
                    } else {
                        inpReader.close();
                    }
                }
                inputData = inpReader.readLine();
            }
            return DataInOutStatus.SUCCESFULLY;
        } catch (IOException e) {
            return DataInOutStatus.FAILED;
        }
    }
}
