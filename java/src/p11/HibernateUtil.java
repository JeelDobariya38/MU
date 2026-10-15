import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY =
            buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("SessionFactory creation failed: "
                    + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        if (SESSION_FACTORY != null && !SESSION_FACTORY.isClosed()) {
            SESSION_FACTORY.close();
        }
    }
}
