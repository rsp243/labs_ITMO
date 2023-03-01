package client.streams.out;

import client.streams.DataInOutStatus;
import client.streams.in.ExecutionMode;

public class OutStream {
    public static DataInOutStatus outputIntoCLI(String strToCLI, ExecutionMode execMode) {
        if (execMode == ExecutionMode.CLI) {
            System.out.println(strToCLI);
        }
        return DataInOutStatus.SUCCESFULLY;
    }
}
