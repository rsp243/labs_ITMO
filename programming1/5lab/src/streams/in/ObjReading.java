package src.streams.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import src.commands.classes.Command;
import src.commands.classes.CommandController;
import src.commands.classes.CommandType;
import src.streams.DataInOutStatus;

public class ObjReading {
    public ObjReading() {
    }

    public ArrayList<String> objRead(CommandController commandController, String commandName, BufferedReader inpReader,
            DataInOutStatus dataFetchController, String inputData) throws IOException {
        ArrayList<String> extraArguments = new ArrayList<String>();
        if (commandController.getMapOfCommands().containsKey(commandName)) {
            Command commandObj = commandController.getMapOfCommands().get(commandName);
            if (commandObj.getCountOfExtraArgs() >= 1) {
                String key = inputData.split(" ")[1];
                extraArguments.add(key);
                if (commandObj.getCountOfExtraArgs() > 1) {
                    System.out.println("Type extra data of object.");
                    if (commandObj.getCommandType() == CommandType.CITY_WORKER) {
                        System.out.println("Type City's name.");
                        extraArguments.add(inpReader.readLine().trim());
                        System.out.println("Type City's coordinates in format 'x y'. Both coordinates x and y are doubles.");
                        extraArguments.add(inpReader.readLine().trim());
                        System.out.println("Type City's area.");
                        extraArguments.add(inpReader.readLine().trim());
                        System.out.println("Type City's population.");
                        extraArguments.add(inpReader.readLine().trim());
                        System.out.println("Type City's metersAboveSeaLevel.");
                        extraArguments.add(inpReader.readLine().trim());
                        System.out.println("Type City's telephoneCode.");
                        extraArguments.add(inpReader.readLine().trim());
                        System.out.println("Type City's carCode.");
                        extraArguments.add(inpReader.readLine().trim());
                        System.out.println("Type City's climate (enum). \n Values: RAIN_FOREST,\n HUMIDSUBTROPICAL,\n HUMIDCONTINENTAL,\n SUBARCTIC, \n TUNDRA");
                        extraArguments.add(inpReader.readLine().trim());
                        System.out.println("Type City's governor in format: 'age height bithday'. Age and height are Integers, bithday is a Date.");
                        extraArguments.add(inpReader.readLine().trim());
                    }
                }
            }
        }
        return extraArguments;
    }
}