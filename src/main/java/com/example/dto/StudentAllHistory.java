package com.example.dto;

import org.example.enums.BookStatus;

import java.time.LocalDateTime;

public class StudentAllHistory extends StudentTakenBookInfo{
    private String bookAuthor;
    private LocalDateTime returnedDate;
    private BookStatus status;
    public  StudentAllHistory() {
    }
    public StudentAllHistory(String bookAuthor, LocalDateTime returnedDate, BookStatus status) {
        super();
        this.bookAuthor = bookAuthor;
        this.returnedDate = returnedDate;
        this.status = status;
    }


    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentAllHistory{" +
                "bookAuthor='" + bookAuthor + '\'' +
                ", returnedDate=" + returnedDate +
                ", status=" + status +
                '}';
    }
}
