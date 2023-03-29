package com.example.dto;

import java.time.LocalDateTime;

public class StudentsBookInfo {
    private Integer id;
    private String bookTitle;
    private String bookAuthor;
    private LocalDateTime createdDate;
    public StudentsBookInfo() {
    }
    public StudentsBookInfo(Integer id, String bookTitle, String bookAuthor, LocalDateTime createdDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "StudentsBookInfo{" +
                "orderNumber=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", BookAuthor='" + bookAuthor + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
