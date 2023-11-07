package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory getFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getFactory().close();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration configuration = new Configuration();
        	configuration.configure("hibernate.cfg.xml");
        	System.out.println("Hibernate Configuration loaded");
        	SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }
        catch (Throwable exception) {
            System.err.println("Initial SessionFactory creation failed." + exception.toString());
            throw new ExceptionInInitializerError(exception);
        }
    }
    
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
