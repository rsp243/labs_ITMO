package server.commands;

import java.io.FilenameFilter;
import java.util.ArrayList;

import client.streams.DataInOutStatus;
import client.streams.in.CommandValidator;
import client.streams.in.ExecutionMode;
import client.streams.in.File.FileReader;
import server.data.Receiver;

/**
 * ExecuteScript command
 * Read script from typed file and execute it.
 */

public class ExecuteScript extends Command {
    private ArrayList<String> historyOfFiles;
    private static ArrayList<String> readedCommands = new ArrayList<>();
    private static Integer currentCommand = 0;

    /**
     * Get number of current command parsing
     * 
     * @retur nnumber of current command parsing
     */
    public static Integer getCurrentCommand() {
        return currentCommand;
    }

    /**
     * Set number of current command parsing
     * 
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
        super("execute_script", "execute_script file_name", "Read script from typed file and execute it.", 1,
                CommandType.FILE_WORKER);
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
            historyOfFiles = new ArrayList<>();
            currentCommand = 0;
            readedCommands = new ArrayList<>();
            return execution.append("You have recursion in your script. Failed.").toString();
        }
        historyOfFiles.add(fileName);

        if (readedCommands.size() != 0) {
            ArrayList<String> afterScriptCommands = new ArrayList<>();
            for (int iter = currentCommand + 1; iter < readedCommands.size(); iter++) {
                afterScriptCommands.add(readedCommands.get(iter));
            }
            readedCommands.addAll(new FileReader().readFile(fileName));
            readedCommands.addAll(afterScriptCommands);
        } else {
            readedCommands.addAll(new FileReader().readFile(fileName));
        }
        System.out.println(readedCommands);
        int iter = 0;
        if (readedCommands.size() != 0) {
            while (iter < readedCommands.size()) {
                String commandLine = readedCommands.get(iter);
                if (new CommandValidator().validate(commandLine,
                        ExecutionMode.EXECUTESCRIPT) != DataInOutStatus.SUCCESFULLY) {
                            historyOfFiles.remove(fileName);
                    return execution.append("Check correctness of commands in your script '" + fileName
                            + "'. Failed.\nSome commands can be completed.").toString();
                }
                currentCommand++;
                iter = currentCommand;
            }  
            historyOfFiles.remove(fileName);
            execution.append("Commands from file '" + fileName + "' successfully completed.").toString();
        } else {
            execution.append("There are some errors with file '" + fileName + "'. Try again.").toString();
        }
        return execution.toString();
    }
}