package mypkgTest;

import mypkg.Student;
import mypkg.HibernateUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ApplicationTest {
    /*initializing the log4j system*/
    static Logger log = Logger.getLogger(ApplicationTest.class);

    public static void main(String[] args) {
        /*configuring the logger*/
        BasicConfigurator.configure();
        log.debug("");  //enter message you want to display once logged!!

        /*CREATE: Add a student record in the database*/
        Student c1 = new Student();
        c1.setStudentId(001);
        c1.setName("Gabby");
        c1.setCourse("Science");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //instantiate the session factory and create a session factory object
        Session session = sessionFactory.openSession(); //open a new session from the session object
        session.beginTransaction(); //begin a transaction
        session.save(c1); //save user object created
        session.getTransaction().commit(); //end the transaction

    }
}
