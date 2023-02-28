package server.commands.classes;

import java.util.ArrayList;
import java.util.LinkedList;

import client.streams.in.File.FileReader;
import server.data.classes.CollectionWorker;

public class ExecuteScript extends Command {
    LinkedList<String> historyOfFiles;
    
    public ExecuteScript() {
        super("execute_script", "Read script from typed file and execute it.", 1, CommandType.FILE_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments) {
        StringBuilder execution = new StringBuilder();
        String fileName = extraArguments.get(0);
        historyOfFiles.add(fileName);
        ArrayList<String> readedCommands = new FileReader().readFile(fileName);
        System.out.println(readedCommands);
        return execution.toString();
    }
}