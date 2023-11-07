package db;

import java.sql.SQLException;
import java.util.List;

public interface CheckDAO<T> {
    void addNewObj(T obj) throws SQLException;
    T getObjById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    void deleteObj(T result) throws SQLException;
    void deleteAll() throws SQLException;
}