import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import client.streams.in.CLI.InputCLIstream;
import server.commands.Invoker;
import server.data.City;
import server.data.LocalDatabase;
import server.data.Receiver;

/**
 * Main class
 * 
 * @author rsp243 - Reshetov Semjon
 * @version 1.0.0
 */
class Main {
    public static void main(String[] args) {
        /**
         * Initialasing LocalDatabase, CollectionWorker, Opening InputCLIStream,
         * generating CommandController.
         * LocalDatabase, InputCLIStream, CommandController
        //  */

        LocalDatabase localDatabase = new LocalDatabase(new ArrayList<City>());
        Receiver receiver = new Receiver(localDatabase);
        new InputCLIstream();
        new Invoker(receiver);
        InputCLIstream.openCLIStream(ExecutionMode.CLI);
    }
}
