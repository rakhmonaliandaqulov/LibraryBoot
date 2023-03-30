package com.example.repository;
import com.example.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public int save(BookEntity book) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(book);
        transaction.commit();

        session.close();
        factory.close();
        return 1;
    }
    /*public int save(Book book) {
        Connection connection = Database.getConnection();
        try {
            String sql = "insert into book (title, author, pulish_year, amount) " + " values ('%s','%s','%s','%s')";
            sql = String.format(sql, book.getTitle(), book.getAuthor(), book.getPublishYear(), book.getAmount());

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
    public List<BookEntity> bookList() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<BookEntity> bookDtoList = session.createQuery("from BookEntity", BookEntity.class).getResultList();
        transaction.commit();

        session.close();
        factory.close();
        return bookDtoList;
    }

    /*public List<Book> getList() {
        List<Book> result = new LinkedList<>();
        try {
            Connection connection = Database.getConnection();
            String sql = "select * from book ";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Integer bookId = resultSet.getInt("id");
                String bookTitle = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publish_year = resultSet.getString("publish_year");
                Integer amount  = resultSet.getInt("amount");
                Boolean visible = resultSet.getBoolean("visible");

                Book book = new Book();
                book.setId(bookId);
                book.setTitle(bookTitle);
                book.setAuthor(author);
                book.setPublishYear(publish_year);
                book.setAmount(amount);
                book.setVisible(visible);

                result.add(book);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }*/
    public BookEntity getBookById(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        BookEntity roomList = session.createQuery("from BookEntity where id = " + id + "", BookEntity.class).getSingleResult();
        transaction.commit();

        session.close();
        factory.close();
        return roomList;
    }
    /*public int deleteBook(Integer id) {
        try (Connection connection = Database.getConnection()) {
            String sql = String.format("delete from book where id = '%s'", id);

            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }*/
    public int deleteBook(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<BookEntity> roomList = session.createQuery("delete from  BookEntity where id = " + id + "", BookEntity.class).getResultList();
        transaction.commit();

        session.close();
        factory.close();
        return 1;
    }

    public int updateBook(Integer id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(id);
        transaction.commit();

        session.close();
        factory.close();
        return 1;
    }
}
