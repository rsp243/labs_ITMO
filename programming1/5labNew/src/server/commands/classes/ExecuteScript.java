package server.commands.classes;

import java.util.ArrayList;

import client.streams.DataInOutStatus;
import client.streams.in.CommandParser;
import client.streams.in.ExecutionMode;
import client.streams.in.File.FileReader;
import server.data.classes.CollectionWorker;

public class ExecuteScript extends Command {
    private ArrayList<String> historyOfFiles;
    private CommandController controller;
    private static ArrayList<String> readedCommands;
    private static Integer currentCommand;

    public static Integer getCurrentCommand() {
        return currentCommand;
    }

    public static void setCurrentCommand(Integer currentCommand) {
        ExecuteScript.currentCommand = currentCommand;
    }

    public static ArrayList<String> getReadedCommands() {
        return readedCommands;
    }

    public ExecuteScript(CommandController aController) {
        super("execute_script", "Read script from typed file and execute it.", 1, CommandType.FILE_WORKER);
        controller = aController;
        historyOfFiles = new ArrayList<>();
    }

    public ArrayList<String> getHistoryOfFiles() {
        return historyOfFiles;
    }

    public CommandController getController() {
        return controller;
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        // execute_script src/server/data/file/executeFile.txt
        StringBuilder execution = new StringBuilder();
        String fileName = extraArguments.get(0);
        if (historyOfFiles.contains(fileName)) {
            return execution.append("You have recursion in your script. Failed.").toString();
        }
        historyOfFiles.add(fileName);
        currentCommand = 0;
        readedCommands = new FileReader().readFile(fileName);
        int iter = 0;
        while (iter < readedCommands.size()) {
            String commandLine = readedCommands.get(iter);
            if (new CommandParser().parse(commandLine, ExecutionMode.EXECUTESCRIPT) != DataInOutStatus.SUCCESFULLY) {
                return execution.append("Check correctness of commands in your script '" + fileName
                        + "'. Failed.\nSome commands can be completed.").toString();
            }
            currentCommand++;
            iter = currentCommand;
        }
        execution.append("Commands from file '" + fileName + "' successfully completed.").toString();
        return execution.toString();
    }
}