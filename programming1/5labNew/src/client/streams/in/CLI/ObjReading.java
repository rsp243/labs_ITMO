package client.streams.in.CLI;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.streams.in.ExecutionMode;
import client.streams.out.OutCLIstream;
import server.commands.classes.Command;
import server.commands.classes.CommandType;
import server.commands.classes.ExecuteScript;
import server.data.classes.Validators.classes.CityValidator.CityValidator;

public class ObjReading {
    public ArrayList<String> objRead(Command commandObj, LinkedHashMap<String, String> fields, ExecutionMode execMode) {
        ArrayList<String> extraArguments = new ArrayList<String>();
        try {
            OutCLIstream.outputIntoCLI("Type extra data of object.", execMode);
            if (commandObj.getCommandType() == CommandType.CITY_WORKER) {
                if (execMode == ExecutionMode.CLI) {
                    for (String field : fields.keySet()) {
                        if (!field.equals("City.id") && !field.equals("City.creationDate")
                                && !field.equals("City.Human.birthday")) {
                            OutCLIstream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                    + fields.get(field) + ". > ", execMode);
                            extraArguments.add(InputCLIstream.getInpReader().readLine().trim());

                        }
                        if (field.equals("City.Human.birthday")) {
                            OutCLIstream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                    + fields.get(field) + ". Type it in format 'day.month.year' > ", execMode);
                            extraArguments.add(InputCLIstream.getInpReader().readLine().trim());
                        }
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
                } catch (NumberFormatException | IndexOutOfBoundsException | DateTimeException m) {
                    extraArguments = new ArrayList<String>();
                }
            }
        } catch (IOException | NullPointerException e) {
            OutCLIstream.outputIntoCLI("\nInterrupting input stream.\n", execMode);
            extraArguments = new ArrayList<String>();
        }
        return extraArguments;
    }
}
