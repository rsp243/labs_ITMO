package client.streams.in.CLI;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.streams.in.ExecutionMode;
import client.streams.out.OutStream;
import server.commands.classes.Command;
import server.commands.classes.CommandType;
import server.commands.classes.ExecuteScript;
import server.data.classes.Validators.classes.CityValidator.CityValidator;

/**
 * ObjReading class
 * Creating if current command need some mre arguments to execute it.
 * if inputed values are wrong, return emty ArrayList, else return ArrayList with String values
 * Use FieldFetcher to getch fields of main Class 
 */

public class ObjReading {
    public ArrayList<String> objRead(Command commandObj, LinkedHashMap<String, String> fields, ExecutionMode execMode) {
        ArrayList<String> extraArguments = new ArrayList<String>();
        try {
            OutStream.outputIntoCLI("Type extra data of object.", execMode);
            if (commandObj.getCommandType() == CommandType.CITY_WORKER) {
                if (execMode == ExecutionMode.CLI) {
                    for (String field : fields.keySet()) {
                        if (!field.equals("City.id") && !field.equals("City.creationDate")
                                && !field.equals("City.Human.birthday")) {
                            OutStream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                    + fields.get(field) + ". > ", execMode);
                        }
                        String value = InputCLIstream.getInpReader().readLine().trim();
                        extraArguments.add(value);
                        if (field.equals("City.Human.birthday")) {
                            OutStream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                    + fields.get(field) + ". Type it in format 'day.month.year' > ", execMode);
                        }
                        String valueOfField = InputCLIstream.getInpReader().readLine().trim();
                        extraArguments.add(valueOfField);
                    }
                } else {
                    if (ExecuteScript.getReadedCommands().size() - ExecuteScript
                            .getCurrentCommand() < ExecuteScript.getCurrentCommand() + fields.size() - 1) {
                        return new ArrayList<String>();
                    }
                    int startValue = ExecuteScript.getCurrentCommand();
                    for (int iter = startValue + 1; iter < ExecuteScript.getReadedCommands().size()
                            - startValue; iter++) {
                        extraArguments.add(ExecuteScript.getReadedCommands().get(iter).trim());
                        ExecuteScript.setCurrentCommand(ExecuteScript.getCurrentCommand() + 1);
                    }
                }
                try {
                    if (!new CityValidator().validate(extraArguments)) {
                        extraArguments = new ArrayList<String>();
                    }
                } catch (IndexOutOfBoundsException | DateTimeException |  IllegalArgumentException m) {
                    extraArguments = new ArrayList<String>();
                }
            }
        } catch (IOException | NullPointerException e) {
            OutStream.outputIntoCLI("\nInterrupting input stream.\n", execMode);
            extraArguments = new ArrayList<String>();
        }
        return extraArguments;
    }
}
