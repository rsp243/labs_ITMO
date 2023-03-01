package server.commands.classes;

import java.util.ArrayList;

import client.streams.in.ExecutionMode;
import server.data.classes.CollectionWorker;

/**
 * SumOfCarCodeCommand command
 * Output sum of values of field 'car code' of all elements from the main collection.
 */

public class SumOfCarCodeCommand extends Command {
    public SumOfCarCodeCommand() {
        super("sum_of_car_code", "Output sum of values of field 'car code' of all elements from the main collection.", 0, CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments, ExecutionMode execMode) {
        return "Sum of car codes of all elements from the main collection: " + worker.sum_of_car_code().toString();
    }
}