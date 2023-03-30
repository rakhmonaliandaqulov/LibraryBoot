package com.example.repository;

import com.example.entity.BookEntity;
import com.example.entity.StudentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private SessionFactory sessionFactory;;
    /*public int save(Student student) {
        Connection connection = Database.getConnection();
        try {
            String sql = "insert into student (name, surname, phone, status, birth_date, visible) " + " values ('%s','%s','%s','%s','%s', '%s')";
            sql = String.format(sql, student.getName(), student.getSurname(), student.getPhone(), student.getStatus(), student.getBirthDate());

            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }*/
    public int save(StudentEntity student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();

        session.close();
        sessionFactory.close();
        return 1;
    }
    public StudentEntity getStudentByPhone(String phone) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        StudentEntity studentEntity = session.createQuery("from StudentEntity where phone = " + phone + "", StudentEntity.class).getSingleResult();
        transaction.commit();

        session.close();
        sessionFactory.close();
        return studentEntity;
    }
    public StudentEntity getStudentById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        StudentEntity studentEntity = session.createQuery("from StudentEntity where id = " + id + "", StudentEntity.class).getSingleResult();
        transaction.commit();

        session.close();
        sessionFactory.close();
        return studentEntity;
    }
    /* public List<Student> getStudentList() {
         try (Connection connection = Database.getConnection()) {
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from student");
             List<Student> studentList = new LinkedList<>();
             while (resultSet.next()) {
                 Student student = new Student();
                 student.setId(resultSet.getInt("id"));
                 student.setName(resultSet.getString("name"));
                 student.setSurname(resultSet.getString("surname"));
                 student.setPhone(resultSet.getString("phone"));
                 student.setBirthDate(resultSet.getDate("birth_daye").toLocalDate());

                 studentList.add(student);
             }
             return studentList;
         } catch (SQLException e) {
             e.printStackTrace();
             System.exit(-1);
         }
         return null;
     }*/
    public List<StudentEntity> studentList() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<StudentEntity> studentEntityList = session.createQuery("from StudentEntity", StudentEntity.class).getResultList();
        transaction.commit();

        session.close();
        sessionFactory.close();
        return studentEntityList;
    }
    public int deleteStudent(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<StudentEntity> studentEntityList = session.createQuery("delete from  StudentEntity where id = " + id + "", StudentEntity.class).getResultList();
        transaction.commit();

        session.close();
        sessionFactory.close();
        return 1;
    }

}
