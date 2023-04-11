package client.streams.in;

import java.util.ArrayList;

import client.streams.DataInOutStatus;

public class CommandValidator {
    private String commandName;
    private ArrayList<String> commandArguments = new ArrayList<>();
    
    public DataInOutStatus validate(String inputData, ExecutionMode execMode) {
        String[] splittedInputData = inputData.split(" ");
        commandName = splittedInputData[0];
        String extraArgument = inputData.replace(commandName, "").trim(); 
        if (!extraArgument.equals("")) {
            commandArguments.add(extraArgument);
        }
        return new CommandChecker().checkCorrectnessOfCommand(commandName, commandArguments, execMode);
    }
}
