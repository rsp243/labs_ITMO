package client;

import java.util.LinkedHashMap;
import java.util.Map;

import server.commands.classes.Command;
import server.commands.classes.CommandController;
import server.data.classes.FieldFetcher;

public class MetaInfoCommand {
    private static Map<String, Command> mapOfCommands;
    private static LinkedHashMap<String, String> fields;

    public MetaInfoCommand() {
        mapOfCommands = CommandController.getMapOfCommands();
    }
    
    public static LinkedHashMap<String, String> getFields() {
        return fields;
    }
    public static void setFields() {
        fields = new FieldFetcher().fetchFields();
    }



    public Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }


}
