package main;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import client.streams.in.CLI.InputCLIstream;
import server.commands.classes.CommandController;
import server.data.classes.City;
import server.data.classes.CollectionWorker;
import server.data.classes.LocalDatabase;
/**
 * Main class
 * @author rsp243 - Reshetov Semjon
 * @version 1.0.0
 */
class Main {
    public static void main(String[] args) {
        /**
         * Initialasing LocalDatabase, CollectionWorker, Opening InputCLIStream, generating CommandController.
         * @see LocalDatabase, InputCLIStream, CommandController
         * */
        LocalDatabase localDatabase = new LocalDatabase(new ArrayList<City>());
        CollectionWorker collectionWorker = new CollectionWorker(localDatabase);
        new InputCLIstream();
        new CommandController(collectionWorker);
        InputCLIstream.openCLIStream(ExecutionMode.CLI);
    }
}