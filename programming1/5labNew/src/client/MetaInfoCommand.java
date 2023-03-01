package client;

import java.util.LinkedHashMap;
import java.util.Map;

import server.commands.classes.Command;
import server.commands.classes.CommandController;
import server.data.classes.FieldFetcher;

/**
 * MetaInfoCommand class contains Map of all commands and fields of the managed class
 */

public class MetaInfoCommand {
    private static Map<String, Command> mapOfCommands;
    private static LinkedHashMap<String, String> fields;

    public MetaInfoCommand() {
        mapOfCommands = CommandController.getMapOfCommands();
    }
    
    /**
     * Get fields
     * @return Map of fields
     */
    public static LinkedHashMap<String, String> getFields() {
        return fields;
    }

    /**
     * Set fields with class FieldFetcher
     */
    public static void setFields() {
        fields = new FieldFetcher().fetchFields();
    }

    /**
     * Get Map of all commands
     */
    public Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }
}
