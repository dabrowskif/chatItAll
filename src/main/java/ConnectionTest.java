import hibernate.entities.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernate.entities.User;

import java.util.List;

import static hibernate.entities.User.StatusEnum.OFF;

public class ConnectionTest {


    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(User.class)
                                .addAnnotatedClass(UserInfo.class)
                                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();




            session.getTransaction().commit();



        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            factory.close();
        }
    }

    private void openConnection() {

    }





}


            /*//add user
            Users user = new Users("filipp", "filipowski", 123456, "OFF");
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

            //list users
            session = factory.getCurrentSession();
            session.beginTransaction();
            List users = session.createQuery("from Users").getResultList();
            displayUsers(users);
            session.getTransaction().commit();

            //update "downloaded" user
            session = factory.getCurrentSession();
            session.beginTransaction();
            int UsersId = 1;
            Users Users = session.get(hibernate.entities.Users.class, UsersId);
            Users.setLogin("MAKABRA");
            User = session.createQuery("from Users").getResultList();
            displayUsers(user);
            session.getTransaction().commit();

            //update users with query
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Users set password='changedpassword'").executeUpdate();
            user = session.createQuery("from Users").getResultList();
            displayUser(user);
            session.getTransaction().commit();
            //delete student
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete Users where id = 3").executeUpdate();
            session.getTransaction().commit();*/