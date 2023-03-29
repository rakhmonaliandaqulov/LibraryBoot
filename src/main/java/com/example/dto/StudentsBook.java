package com.example.dto;

import org.example.enums.BookStatus;

import java.time.LocalDateTime;

public class StudentsBook {
    private Integer id;
    private LocalDateTime createdDate;
    private BookStatus status;
    private LocalDateTime returnedDate;
    private Integer duration;
    private Book bookId;
    private Student studentId;
    public StudentsBook() {
    }

    public StudentsBook(Integer id, LocalDateTime createdDate, BookStatus status, LocalDateTime returnedDate,
                        Integer duration, Book bookId, Student studentId) {
        this.id = id;
        this.createdDate = createdDate;
        this.status = status;
        this.returnedDate = returnedDate;
        this.duration = duration;
        this.bookId = bookId;
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book id) {
        this.bookId = id;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student id) {
        this.studentId = id;
    }

    @Override
    public String toString() {
        return "StudentsBook{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", returnedDate=" + returnedDate +
                ", duration=" + duration +
                '}';
    }
}
