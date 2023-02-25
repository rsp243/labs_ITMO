package client;

import java.util.LinkedHashMap;
import java.util.Map;

import server.commands.classes.Command;
import server.commands.classes.CommandController;
import server.data.classes.FieldFetcher;

public class MetaInfoCommand {
    private Map<String, Command> mapOfCommands;
    private LinkedHashMap<String, String> fields;

    public LinkedHashMap<String, String> getFields() {
        return fields;
    }
    public void setFields() {
        this.fields = new FieldFetcher().fetchFields();
    }

    public MetaInfoCommand() {
        mapOfCommands = CommandController.getMapOfCommands();
    }

    public Map<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }


}
