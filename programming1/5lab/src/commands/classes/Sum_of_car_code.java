package src.commands.classes;

import java.util.ArrayList;

import src.data.classes.CollectionWorker;

public class Sum_of_car_code extends Command {
    public Sum_of_car_code() {
        super("sum_of_car_code", "Output sum of values of field 'car code' of all elements from the main collection.", 0, CommandType.COLLECTION_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, String nameOfCommand, ArrayList<String> extraArguments) {
        return "Sum of car codes of all elements from the main collection: " + worker.sum_of_car_code().toString();
    }
}