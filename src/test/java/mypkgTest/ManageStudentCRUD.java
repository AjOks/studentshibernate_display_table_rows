package mypkgTest;

import mypkg.Student;
import mypkg.HibernateUtil;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Iterator;


public class ManageStudentCRUD {
    /*initializing the log4j system*/
    static Logger log = Logger.getLogger(ApplicationTest.class);

    public static void main(String[] args) {
        /*configuring the logger*/
        BasicConfigurator.configure();
        log.debug("");  //enter message you want to display once logged!!

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); //instantiate the session factory and get a session factory object

        ManageStudentCRUD MS = new ManageStudentCRUD();

        /*Create / Add few student records in the database*/
        Integer studentID1 = MS.addStudent("AJOks", "Java");
        Integer studentID2 = MS.addStudent("Tony", "Programming");
        Integer studentID3 = MS.addStudent("Gabby", "Science");

        /* List down all the employees */
        MS.listStudents();

      /* Update employee's records */
        MS.updateStudent(3,"Maths");      //<-- indicate employee's Id as stored in the database

      /* Delete an employee from the database */
        MS.deleteStudent(10);              //<-- indicate employee's Id as stored in the database

      /* List down new list of the employees */
        //MS.listStudents();

    }

    /* Method to CREATE a student in the database */
    public Integer addStudent(String name, String course){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer studentID = null;
        try{
            tx = session.beginTransaction();
            Student student = new Student();
            student.setName(name);
            student.setCourse(course);
            studentID = (Integer) session.save(student);  //saves the student + retrieves the database generated ID + assigns the ID to studentID
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return studentID;
    }

    /* Method to  READ all the students */
    public void listStudents( ){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List students = session.createQuery("FROM Student").list();    //gets: select * From Student list
            for (Iterator iterator = students.iterator(); iterator.hasNext();){    //loops through lidt of students
                Student student = (Student) iterator.next();
                System.out.print("Name: " + student.getName());
                System.out.print("  Course: " + student.getCourse());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to UPDATE course for a student */
    public void updateStudent(Integer studentID, String course){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Student student =      //gets: select studentId from Student, where studentId = "studentID"
                    (Student)session.get(Student.class, studentID);
            student.setCourse(course);    //updates course with new value
            session.update(student);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE a student from the records */
    public void deleteStudent(Integer studentID){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Student student =       //gets: select studentId from Student, where studentId = "studentID"
                    (Student)session.get(Student.class, studentID);
            session.delete(student );  //delete student
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
