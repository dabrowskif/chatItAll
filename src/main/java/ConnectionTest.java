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


            User tmpUser1 = new User("fufu", "fufupass", 123456, OFF);
            User tmpUser2 = new User("maro", "maropass", 123457, OFF);
            User tmpUser3 = new User("jaro", "jaropass", 123458, OFF);
            User tmpUser4 = new User("karo", "karopass", 123459, OFF);


            tmpUser1.addFriend(tmpUser2);
            tmpUser1.addFriend(tmpUser3);
            tmpUser1.addFriend(tmpUser4);

            tmpUser2.addFriend(tmpUser3);
            tmpUser2.addFriend(tmpUser4);


            session.beginTransaction();


            session.save(tmpUser1);
            session.save(tmpUser2);
            session.save(tmpUser3);
            session.save(tmpUser4);



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
            List Userss = session.createQuery("from Users").getResultList();
            displayUserss(Userss);
            session.getTransaction().commit();

            //update "downloaded" user
            session = factory.getCurrentSession();
            session.beginTransaction();
            int UsersId = 1;
            Users Users = session.get(hibernate.entities.Users.class, UsersId);
            Users.setLogin("MAKABRA");
            Userss = session.createQuery("from Users").getResultList();
            displayUserss(Userss);
            session.getTransaction().commit();

            //update users with query
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Users set password='changedpassword'").executeUpdate();
            Userss = session.createQuery("from Users").getResultList();
            displayUserss(Userss);
            session.getTransaction().commit();
            //delete student
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete Users where id = 3").executeUpdate();
            session.getTransaction().commit();*/