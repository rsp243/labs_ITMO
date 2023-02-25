package src.client;

public class CommandController {
    
    public boolean checkingCorrectnessOfCommand() {
        CommandDataChecker commandChecker = new CommandDataChecker();
        // if (commandChecker.checkInputedCommand(commandController, inpReader, outputStream,
        //         inputData, fields) == DataInOutStatus.FAILED) {
        //     outputStream.outputIntoCLI("You have typed wrong arguments to last command. Try again. \n");
        // } else {
        //     ArrayList<String> extraArguments = commandChecker.getExtraArguments();
        //     if (commandController.getMapOfCommands().containsKey(commandName)) {
        //         Command commandObj = commandController.getMapOfCommands().get(commandName);
        //         if (commandObj.getCountOfExtraArgs() >= 1) {
        //             if (splittedInputData.length == 2) {
        //                 String key = inputData.split(" ")[1];
        //                 extraArguments.add(0, key);
        //             }
        //         }
        //     }
        //     outputStream.openOutputStream(commandController, fields, commandName,
        //             extraArguments);
        // }

        return true;
    }
}
