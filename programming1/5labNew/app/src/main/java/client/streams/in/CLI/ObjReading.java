package client.streams.in.CLI;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.streams.in.ExecutionMode;
import client.streams.out.OutStream;
import server.commands.Command;
import server.commands.CommandType;
import server.commands.ExecuteScript;
import server.data.Validators.AbstractValidator;
import server.data.Validators.ValidatorManager;
import server.data.Validators.CityValidator.CityValidator;

/**
 * ObjReading class
 * Creating if current command need some mre arguments to execute it.
 * if inputed values are wrong, return emty ArrayList, else return ArrayList
 * with String values
 * Use FieldFetcher to getch fields of main Class
 */

public class ObjReading {
    public ArrayList<String> objRead(Command commandObj, LinkedHashMap<String, String> fields, ExecutionMode execMode) {
        ArrayList<String> extraArguments = new ArrayList<String>();
        try {
            OutStream.outputIntoCLI("Type extra data for object.", execMode);
            if (commandObj.getCommandType() == CommandType.CITY_WORKER) {
                ValidatorManager validatorManager = new ValidatorManager(fields);
                if (execMode == ExecutionMode.CLI) {
                    int iter = 1;
                    while (iter < fields.keySet().size()) {
                        String field = fields.keySet().toArray()[iter].toString();
                        AbstractValidator validator = validatorManager.getValidator(field);
                        if (validator == null) {
                            iter++;
                            continue;
                        }
                        if (!field.equals("City.id") && !field.equals("City.creationDate")
                                && !field.equals("City.Human.birthday")) {
                            OutStream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                    + fields.get(field) + ". > ", execMode);
                        }
                        if (field.equals("City.Human.birthday")) {
                            OutStream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                    + fields.get(field) + ". Type it in format 'day.month.year' > ", execMode);
                        }
                        String valueOfField = InputCLIstream.getInpReader().readLine().trim();

                        // Validation of typed argument with validatorManager.
                        try {
                            if (validator.validate(validator.caster(valueOfField))) {
                                extraArguments.add(valueOfField);
                                iter++;
                            } else {
                                OutStream.outputIntoCLI("You've typed wrong value of field. Restrictions to that field: " + validator.getRestrictions() + ".", execMode);
                            }
                        } catch (IndexOutOfBoundsException | DateTimeException | IllegalArgumentException m) {
                            OutStream.outputIntoCLI("You've typed wrong value of field. Check that you type right type of field: " + fields.get(field) + ".",
                                    execMode);
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
            }
        } catch (IOException | NullPointerException e) {
            OutStream.outputIntoCLI("\nInterrupting input stream.\n", execMode);
            extraArguments = new ArrayList<String>();
        }
        return extraArguments;
    }
}
