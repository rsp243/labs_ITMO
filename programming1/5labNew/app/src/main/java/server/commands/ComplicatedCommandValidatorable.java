package server.commands;

import java.util.ArrayList;

import client.streams.DataInOutStatus;
import client.streams.in.ExecutionMode;

public interface ComplicatedCommandValidatorable {
    public DataInOutStatus checkCorrectnessOfComplicatedCommand(Command commandObj,
    ArrayList<String> argumentsToCommand,
    ExecutionMode execMode);
}
