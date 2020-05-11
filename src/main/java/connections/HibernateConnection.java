package connections;

import hibernate.entities.Friend;
import hibernate.entities.User;
import hibernate.entities.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

import static hibernate.entities.User.StatusEnum;
import static hibernate.entities.User.StatusEnum.*;


public class HibernateConnection {

    private static volatile HibernateConnection hibConnInstance;

    SessionFactory sessionFactory;
    Session session;

    private HibernateConnection() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(UserInfo.class)
                .addAnnotatedClass(Friend.class)
                .buildSessionFactory();

    }

    public static HibernateConnection getInstance() {
        if (hibConnInstance == null) {
            hibConnInstance = new HibernateConnection();
        }
        return hibConnInstance;
    }

    public User findUser(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            /*isCorrect = session.createQuery("from User u where u.login = :login and u.password = :password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .uniqueResult() != null;*/
            user = session.createQuery("select u from User u where u.login = :login and u.password = :password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public void setUserStatus(User user, StatusEnum statusEnum) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            user.setStatus(statusEnum);

            session.update(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<User> getUserFriends(User user) {
        List<User> friendList = new ArrayList<>();

        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            friendList = user.getFriends();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return friendList;
    }

    public void addDatabaseData() {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            User tmpUser1 = new User("fufu", "fufupass", 100, OFF);
            User tmpUser2 = new User("maro", "maropass", 101, OFF);
            User tmpUser3 = new User("jaro", "jaropass", 102, OFF);
            User tmpUser4 = new User("karo", "karopass", 103, OFF);

            tmpUser1.addFriend(tmpUser2);
            tmpUser1.addFriend(tmpUser3);

            session.save(tmpUser1);
            session.save(tmpUser2);
            session.save(tmpUser3);
            session.save(tmpUser4);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    //template
    /*private void methodName(User user) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }*/
}
