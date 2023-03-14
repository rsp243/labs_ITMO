import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import client.streams.in.CLI.InputCLIstream;
import server.commands.classes.Invoker;
import server.data.classes.City;
import server.data.classes.LocalDatabase;
import server.data.classes.Receiver;
/**
 * Main class
 * @author rsp243 - Reshetov Semjon
 * @version 1.0.0
 */
class Main {
    public static void main(String[] args) {
        /**
         * Initialasing LocalDatabase, CollectionWorker, Opening InputCLIStream, generating CommandController.
         * LocalDatabase, InputCLIStream, CommandController
         * */
        LocalDatabase localDatabase = new LocalDatabase(new ArrayList<City>());
        Receiver receiver = new Receiver(localDatabase);
        new InputCLIstream();
        new Invoker(receiver);
        InputCLIstream.openCLIStream(ExecutionMode.CLI);
    }
}
