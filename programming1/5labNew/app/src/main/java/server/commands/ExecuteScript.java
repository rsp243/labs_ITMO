package server.commands;

import java.util.ArrayList;

import client.streams.DataInOutStatus;
import client.streams.in.CommandParser;
import client.streams.in.ExecutionMode;
import client.streams.in.File.FileReader;
import server.data.Receiver;

/**
 * ExecuteScript command
 * Read script from typed file and execute it.
 */

public class ExecuteScript extends Command {
    private ArrayList<String> historyOfFiles;
    private static ArrayList<String> readedCommands;
    private static Integer currentCommand;

    /**
     * Get number of current command parsing 
     * @retur nnumber of current command parsing 
     */
    public static Integer getCurrentCommand() {
        return currentCommand;
    }

    /**
     * Set number of current command parsing 
     * @param currentCommand
     */
    public static void setCurrentCommand(Integer currentCommand) {
        ExecuteScript.currentCommand = currentCommand;
    }

    /**
     * 
     * @return
     */
    public static ArrayList<String> getReadedCommands() {
        return readedCommands;
    }

    public ExecuteScript() {
        super("execute_script", "Read script from typed file and execute it.", 1, CommandType.FILE_WORKER);
        historyOfFiles = new ArrayList<>();
    }

    public ArrayList<String> getHistoryOfFiles() {
        return historyOfFiles;
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
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
        if (readedCommands.size() != 0) {
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
        } else {
            execution.append("There are some errors with file '" + fileName + "'. Try again.").toString();
        }
        return execution.toString();
    }
}