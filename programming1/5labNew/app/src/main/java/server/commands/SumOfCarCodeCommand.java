package server.commands;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.Receiver;

/**
 * SumOfCarCodeCommand command
 * Output sum of values of field 'car code' of all elements from the main collection.
 */

public class SumOfCarCodeCommand extends Command {
    public SumOfCarCodeCommand() {
        super("sum_of_car_code", "Output sum of values of field 'car code' of all elements from the main collection.", 0, CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(Receiver worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        return "Sum of car codes of all elements from the main collection: " + worker.getSumOfCarCode().toString();
    }
}