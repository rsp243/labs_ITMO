package client.streams.in;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import client.streams.out.OutCLIstream;
import server.commands.classes.Command;
import server.commands.classes.CommandType;
import server.data.classes.Validators.classes.CityValidator.CityValidator;

public class ObjReading {
    public ArrayList<String> objRead(Command commandObj, LinkedHashMap<String, String> fields) {
        ArrayList<String> extraArguments = new ArrayList<String>();
        try {
            OutCLIstream.outputIntoCLI("Type extra data of object.");
            if (commandObj.getCommandType() == CommandType.CITY_WORKER) {
                for (String field : fields.keySet()) {
                    if (!field.equals("City.id") && !field.equals("City.creationDate") && !field.equals("City.Human.birthday")) {
                        OutCLIstream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                + fields.get(field) + ". > ");
                        extraArguments.add(InputCLIstream.getInpReader().readLine().trim());
                    }
                    if (field.equals("City.Human.birthday")) {
                        OutCLIstream.outputIntoCLI("Type '" + field + "'. Type of '" + field + "' is "
                                + fields.get(field) + ". Type it in format 'day.month.year' > ");
                        extraArguments.add(InputCLIstream.getInpReader().readLine().trim());
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
            OutCLIstream.outputIntoCLI("\nInterrupting input stream.\n");
            extraArguments = new ArrayList<String>();
        }
        return extraArguments;
    }
}
