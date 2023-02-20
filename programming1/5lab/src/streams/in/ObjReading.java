package src.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;

import src.commands.classes.Command;
import src.commands.classes.CommandController;
import src.commands.classes.CommandType;
import src.data.classes.Validators.classes.CityValidator.CityValidator;
import src.streams.out.OutCLIstream;

public class ObjReading {
    public ArrayList<String> objRead(CommandController commandController, BufferedReader inpReader, OutCLIstream outputCLI, String commandName, 
            String inputData) throws IOException {
        ArrayList<String> extraArguments = new ArrayList<String>();
        if (commandController.getMapOfCommands().containsKey(commandName)) {
            Command commandObj = commandController.getMapOfCommands().get(commandName);
            try {
                if (commandObj.getCountOfExtraArgs() > 1) {
                    outputCLI.outputIntoCLI("Type extra data of object. \n");
                    if (commandObj.getCommandType() == CommandType.CITY_WORKER) {
                        outputCLI.outputIntoCLI("Type City's name. > ");
                        extraArguments.add(inpReader.readLine().trim());
                        outputCLI.outputIntoCLI(
                                        "Type City's coordinates in format 'x y'. Both coordinates x and y are long. > ");
                        extraArguments.add(inpReader.readLine().trim());
                        outputCLI.outputIntoCLI("Type City's area. > ");
                        extraArguments.add(inpReader.readLine().trim());
                        outputCLI.outputIntoCLI("Type City's population. > ");
                        extraArguments.add(inpReader.readLine().trim());
                        outputCLI.outputIntoCLI("Type City's metersAboveSeaLevel. > ");
                        extraArguments.add(inpReader.readLine().trim());
                        outputCLI.outputIntoCLI("Type City's telephoneCode. > ");
                        extraArguments.add(inpReader.readLine().trim());
                        outputCLI.outputIntoCLI("Type City's carCode. > ");
                        extraArguments.add(inpReader.readLine().trim());
                        outputCLI.outputIntoCLI(
                                "Type City's climate (enum). \n Values: RAIN_FOREST,\n HUMIDSUBTROPICAL,\n HUMIDCONTINENTAL,\n SUBARCTIC, \n TUNDRA > ");
                        extraArguments.add(inpReader.readLine().trim());
                        outputCLI.outputIntoCLI(
                                "Type City's governor in format: 'age height birthday'. \n Age is Integer, height is Float and birthday is LocalDate. Type birthday in format: 'day.month.year'. > ");
                        extraArguments.add(inpReader.readLine().trim());
                        try {
                            if (!new CityValidator().validate(extraArguments)) {
                                extraArguments = new ArrayList<String>();
                            }
                        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException | DateTimeException m) {
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
