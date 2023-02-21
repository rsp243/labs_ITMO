package src.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.HashMap;

import src.commands.classes.Command;
import src.commands.classes.CommandController;
import src.commands.classes.CommandType;
import src.data.classes.Validators.classes.CityValidator.CityValidator;
import src.streams.out.OutCLIstream;

public class ObjReading {
    public ArrayList<String> objRead(CommandController commandController, BufferedReader inpReader,
            OutCLIstream outputCLI, String commandName,
            String inputData, HashMap<String, String> fields) throws IOException {
        ArrayList<String> extraArguments = new ArrayList<String>();
        if (commandController.getMapOfCommands().containsKey(commandName)) {
            Command commandObj = commandController.getMapOfCommands().get(commandName);
            try {
                if (commandObj.getCountOfExtraArgs() > 1) {
                    outputCLI.outputIntoCLI("Type extra data of object. \n");
                    if (commandObj.getCommandType() == CommandType.CITY_WORKER) {
                        for (String field : fields.keySet()) {
                            if (!field.equals("City.id") && !field.equals("City.creationDate")
                                    && !field.equals("City.Human.birthday")) {
                                outputCLI.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                        + fields.get(field) + ". > ");
                                extraArguments.add(inpReader.readLine().trim());
                            }
                            if (field.equals("City.Human.birthday")) {
                                outputCLI.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                        + fields.get(field) + ". Type it in format 'day.month.year' > ");
                                extraArguments.add(inpReader.readLine().trim());
                            }
                        }
                        try {
                            if (!new CityValidator().validate(extraArguments)) {
                                extraArguments = new ArrayList<String>();
                            }
                        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException
                                | DateTimeException m) {
                            extraArguments = new ArrayList<String>();
                        }
                    }
                }

            } catch (NullPointerException e) {
                outputCLI.outputIntoCLI("\nInterrupting input stream.\n");
                extraArguments = new ArrayList<String>();
            }
        }
        return extraArguments;
    }
}
