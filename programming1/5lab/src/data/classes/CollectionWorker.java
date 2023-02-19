package src.data.classes;

import java.util.Date;
import java.util.LinkedHashMap;

public class CollectionWorker {
    private final LocalDatabase database;

    public CollectionWorker(LocalDatabase aLocalDatabase) {
        database = aLocalDatabase;
    }

    public LinkedHashMap<String, City> getMainCollection() {
        return database.getMainCollection();
    }

    public String addNew(String key, City city) {
        String strStatus = "Successufully";
        this.getMainCollection().put(key, city);
        return strStatus;
    }

    public String removeKey(String key) {
        String strSuccess = "Successufully";
        this.getMainCollection().remove(key);
        return strSuccess;
    }

    public String update(String key, City city) {
        String strSuccess = "Successufully";
        if (this.getMainCollection().containsKey(key)) {
            this.getMainCollection().replace(key, city);
        }
        return strSuccess;
    }

    public String clear() {
        String strSuccess = "Successufully";
        this.getMainCollection().clear();
        return strSuccess;
    }
    
    public Long sum_of_car_code() {
        Long sumCarCode = (long) 0;
        for (City iter : this.getMainCollection().values()) {
            sumCarCode += iter.getCarCode();
        }
        return sumCarCode;
    }

    public void setDateOfLastChange() {
        database.setDateOfLastChange(new Date());
    }

    public LocalDatabase getDataBase() {
        return database;
    }
}
