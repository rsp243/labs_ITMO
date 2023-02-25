package client.streams;

public enum DataInOutStatus {
    SUCCESFULLY("Successfully."),
    FAILED("Something went wrong. Try again."),
    NOCOMMAND("There is no command with that name. Try again. \nUse 'help' to see all list of commands."),
    WRONGARGS("You've typed wrong number of arguments to command. Try again.");

    private String message;

    public String getMessage() {
        return message;
    }

    DataInOutStatus(String aMessage) {
        message = aMessage;
    }
}
