package main;

import java.time.LocalDate;
import java.util.ArrayList;

import client.streams.in.InputCLIstream;
import server.commands.classes.CommandController;
import server.data.classes.City;
import server.data.classes.CollectionWorker;
import server.data.classes.Coordinates;
import server.data.classes.Human;
import server.data.classes.LocalDatabase;
import server.data.enums.Climate;
import server.fillers.Increment;

class Main {
    public static void main(String[] args) {
        LocalDatabase localDatabase = new LocalDatabase(new ArrayList<City>());
        CollectionWorker collectionWorker = new CollectionWorker(localDatabase);
        new CommandController(collectionWorker);
        new InputCLIstream();
        InputCLIstream.openCLIStream();
    }
}