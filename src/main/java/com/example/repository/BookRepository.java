package com.example.repository;

import org.example.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public int save(Book book) {
        String sql = "insert into book (title,author, publish_year, amount) values ('%s','%s','%s','%s')";
        sql = String.format(sql, book.getTitle(), book.getAuthor(), book.getPublishYear(), book.getAmount());
        int n = jdbcTemplate.update(sql);
        return n;
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
    public List<Book> bookList() {
        String sql = "SELECT * FROM book";
        List<Book> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return list;
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
    public Book getBookById(Integer id) {
        String sql = "SELECT * FROM book Where id =" + id;
        List<Book> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
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
        String sql = "delete FROM book Where id =" + id;
        int n = jdbcTemplate.update(sql);
        return n;
    }

    public void updateBook(Integer id, Book book) {
        String sql = "update book set id = %d, title = '%s', author = '%s', publish_year = '%s', amount = '%s', visible = %s where id =" + id;
        sql = String.format(sql, book.getId(), book.getTitle(), book.getAuthor(), book.getPublishYear(),
                book.getAmount(), book.getVisible(), id);
        jdbcTemplate.update(sql);
    }
}
