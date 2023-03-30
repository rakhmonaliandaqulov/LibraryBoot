package com.example.repository;

import com.example.dto.StudentBookDto;
import com.example.entity.StudentBookEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class StudentBookRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public int save(StudentBookEntity studentsBook) {
        String sql = "insert into students_book (created_date,status,returned_date,duration,student_id,book_id) " +
                "values (now(),'%s','%s','%s',%s,%s)";
        sql = String.format(sql, studentsBook.getStatus(),studentsBook.getReturnedDate(),studentsBook.getDuration(),
                studentsBook.getStudentId().getId(), studentsBook.getBookId().getId());
        return jdbcTemplate.update(sql);
    }
    public List<StudentBookDto> getStudentsBookById(Integer id) {
        String sql = " select * from students_book where id = " + id;
        List<StudentBookDto> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentBookDto.class));
        return list;
    }
    public List<StudentsBookInfo> userTakenList() {
        String sql = "select sb.id, b.title as book_title, b.author as book_author, sb.created_date from book as b " +
                "inner join students_book as sb on sb.book_id = b.id " +
                "inner join student as s on s.id = sb.student_id " +
                "where sb.status = 'TAKEN'";
        List<StudentsBookInfo> list = jdbcTemplate.query(sql, new RowMapper<StudentsBookInfo>() {
            @Override
            public StudentsBookInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentsBookInfo sb = new StudentsBookInfo();
                sb.setId(rs.getInt("id"));
                sb.setBookTitle(rs.getString("book_title"));
                sb.setBookAuthor(rs.getString("book_author"));
                sb.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());
                return sb;
            }
        });
        return list;
    }
    public List<StudentBookDto> userReturnedBookList() {
        String sql = "select sb.id, b.title, b.author, sb.created_date from book as b " +
                "inner join students_book as sb on sb.book_id = b.id " +
                "inner join student as s on s.id = sb.student_id " +
                "where sb.status = 'RETURNED'";
        List<StudentBookDto> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentBookDto.class));
        return list;
    }
    public List<StudentBookHistory> userHistoryLibrary() {
        String sql = "select sb.id, b.title as b_title, b.author as b_author, sb.status, sb.created_date as taken_date, " +
                " sb.returned_date from book as b " +
                "inner join students_book as sb on sb.book_id = b.id " +
                "inner join student as s on s.id = sb.student_id ";
        List<StudentBookHistory> list = jdbcTemplate.query(sql, new RowMapper<StudentBookHistory>() {
            @Override
            public StudentBookHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentBookHistory studentBookHistory = new StudentBookHistory();
                studentBookHistory.setId(rs.getInt("id"));
                studentBookHistory.setBookTitle(rs.getString("b_title"));
                studentBookHistory.setBookAuthor(rs.getString("b_author"));
                studentBookHistory.setStatus(BookStatus.valueOf(rs.getString("status")));
                studentBookHistory.setCreatedDate(rs.getTimestamp("taken_date").toLocalDateTime());
                studentBookHistory.setRetunedDate(rs.getTimestamp("returned_date").toLocalDateTime());
                return studentBookHistory;
            }
        });
        return list;
    }
    public List<StudentTakenBookInfo> adminTakenBookStudentList() {
        String sql = "select sb.id, s.name as s_name, s.surname as s_surname, s.phone as s_phone, b.title as b_title, sb.created_date as taken_date from book as b " +
                "inner join students_book as sb on sb.book_id = b.id " +
                "inner join student as s on s.id = sb.student_id " +
                "where sb.status = 'TAKEN'";
        List<StudentTakenBookInfo> list = jdbcTemplate.query(sql, new RowMapper<StudentTakenBookInfo>() {
            @Override
            public StudentTakenBookInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentTakenBookInfo studentInfo = new StudentTakenBookInfo();
                studentInfo.setId(rs.getInt("id"));
                studentInfo.setStudentName(rs.getString("s_name"));
                studentInfo.setStudentSurname(rs.getString("s_surname"));
                studentInfo.setStudentPhone(rs.getString("s_phone"));
                studentInfo.setBookTitle(rs.getString("b_title"));
                studentInfo.setTakenDate(rs.getTimestamp("taken_date").toLocalDateTime());
                return studentInfo;
            }
        });
        return list;
    }
    public List<StudentAllHistory> adminStudentAllHistory() {
        String sql = "select sb.id, s.name as s_name, s.surname as s_surname, s.phone as s_phone, b.title as b_title, " +
                " b.author as b_author, sb.status, sb.created_date as taken_date, sb.returned_date from book as b " +
                "inner join students_book as sb on sb.book_id = b.id " +
                "inner join student as s on s.id = sb.student_id " +
                "where sb.status = 'TAKEN'";
        List<StudentAllHistory> list = jdbcTemplate.query(sql, new RowMapper<StudentAllHistory>() {
            @Override
            public StudentAllHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
                StudentAllHistory studentAllHistory = new StudentAllHistory();
                studentAllHistory.setId(rs.getInt("id"));
                studentAllHistory.setStudentName(rs.getString("s_name"));
                studentAllHistory.setStudentSurname(rs.getString("s_surname"));
                studentAllHistory.setStudentPhone(rs.getString("s_phone"));
                studentAllHistory.setBookTitle(rs.getString("b_title"));
                studentAllHistory.setBookAuthor(rs.getString("b_author"));
                studentAllHistory.setStatus(BookStatus.valueOf(rs.getString("status")));
                studentAllHistory.setTakenDate(rs.getTimestamp("taken_date").toLocalDateTime());
                studentAllHistory.setReturnedDate(rs.getTimestamp("returned_date").toLocalDateTime());
                return studentAllHistory;
            }
        });
        return list;
    }

    public void returnBook(Integer bookId) {
        String sql = "update students_book set status = 'RETURNED' where id =" + bookId;
        jdbcTemplate.update(sql);

    }
}
