package server.commands.classes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import server.data.classes.CollectionWorker;
import server.data.enums.Climate;

public class CountByClimate extends Command {
    public CountByClimate() {
        super("count_by_climate", "Output count of objects, which climate equals typed argument.", 1,
                CommandType.INFO_WORKER);
    }

    @Override
    public String execute(CollectionWorker worker, ArrayList<String> extraArguments) {
        StringBuilder execution = new StringBuilder();
        int count = 0;
        LinkedHashMap<String, server.data.classes.City> maintCollection = worker.getMainCollection();
        try {
            Climate valueOfClimate = Climate.valueOf(extraArguments.get(0));
            for (String key : maintCollection.keySet()) {
                if (maintCollection.get(key).getClimate() == valueOfClimate) {
                    count++;
                }
            }
            execution.append("Count of objects with enumValue " + valueOfClimate + ": " + count);
        } catch (IllegalArgumentException e) {
            return execution.append("There is no Enum Value with typed argument. Try again.").toString();
        }
        return execution.toString();
    }
}
