package client;

import java.util.LinkedHashMap;
import java.util.Map;

import server.commands.Command;
import server.commands.Invoker;
import server.data.FieldFetcher;

/**
 * MetaInfoCommand class contains Map of all commands and fields of the managed class
 */

public class MetaInfoCommand {
    private static Map<String, Command> mapOfCommands;
    private static LinkedHashMap<String, String> fields;

    public MetaInfoCommand() {
        mapOfCommands = Invoker.getMapOfCommands();
        fields = new FieldFetcher().fetchFields();
    }
    
    /**
     * Get fields
     * @return Map of fields
     */
    public static LinkedHashMap<String, String> getFields() {
        return fields;
    }

    /**
     * Get Map of all commands
     */
    public Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }
}
