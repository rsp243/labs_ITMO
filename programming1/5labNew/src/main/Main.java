package main;

import java.util.ArrayList;

import client.streams.in.InputCLIstream;
import server.commands.classes.CommandController;
import server.data.classes.City;
import server.data.classes.CollectionWorker;
import server.data.classes.LocalDatabase;

class Main {
    public static void main(String[] args) {
        LocalDatabase localDatabase = new LocalDatabase(new ArrayList<City>());
        CollectionWorker collectionWorker = new CollectionWorker(localDatabase);
        new InputCLIstream();
        new CommandController(collectionWorker);
        InputCLIstream.openCLIStream();
    }
}