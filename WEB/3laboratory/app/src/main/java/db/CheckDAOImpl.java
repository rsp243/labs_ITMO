package db;

import jakarta.persistence.criteria.Root;
import model.ResultBean;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class CheckDAOImpl implements CheckDAO<ResultBean> {
    @Override
    public void addNewObj(ResultBean result) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();

            // start transaction
            session.beginTransaction();
    
            // Save the ResultBean to database
            session.persist(result);;
            
            // Commit the transaction
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println("Something went wrong in DAO: " + exception);
            throw new SQLException(exception);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public ResultBean getObjById(int id) throws SQLException {
        Session session = null;
        ResultBean result;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            result = session.getReference(ResultBean.class, id);
        } catch (Throwable exception) {
            System.err.println("Something went wrong in DAO: " + exception);
            throw new SQLException(exception);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    @Override
    public List<ResultBean> getAll() throws SQLException {
        Session session = null;
        List<ResultBean> results;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            var criteriaQuery = session.getCriteriaBuilder().createQuery(ResultBean.class);
            Root<ResultBean> root = criteriaQuery.from(ResultBean.class);
            results = session.createQuery(criteriaQuery.select(root)).getResultList();
            System.out.println("Got " + results.size() + " objects from DB.");
        } catch (Throwable e) {
            System.err.println("Error: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return results;
    }

    @Override
    public void deleteObj(ResultBean result) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();

            // start transaction
            session.beginTransaction();
    
            // Remove the ResultBean from database
            session.remove(result);
            
            // Commit the transaction
            session.getTransaction().commit();
        } catch (Throwable exception) {
            System.err.println("Something went wrong in DAO: " + exception);
            throw new SQLException(exception);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getFactory().openSession();

            // start transaction
            session.beginTransaction();

            // Truncate database
            String sql = "DELETE FROM results";
            session.createNativeQuery(sql, this.getClass()).executeUpdate();

            // Commit the transaction
            session.getTransaction().commit();
        } catch (Throwable e) {
            System.err.println("Error: " + e);
            throw new SQLException(e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}