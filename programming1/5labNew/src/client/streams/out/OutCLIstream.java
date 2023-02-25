package client.streams.out;

import client.streams.DataInOutStatus;

public class OutCLIstream {
    public static DataInOutStatus outputIntoCLI(String strToCLI) {
        System.out.println(strToCLI);
        return DataInOutStatus.SUCCESFULLY;
    }
}
