package db;

import model.ResultBean;

public class DAOFactory {
    private static CheckDAO<ResultBean> resultDAO;

    private static DAOFactory instance;

    public static DAOFactory getInstance() {
        if (instance == null)
            instance = new DAOFactory();
        return instance;
    }
    
    public CheckDAO<ResultBean> getResultDAO() {
        if (resultDAO == null)
            resultDAO = new CheckDAOImpl();
        return resultDAO;
    }
}