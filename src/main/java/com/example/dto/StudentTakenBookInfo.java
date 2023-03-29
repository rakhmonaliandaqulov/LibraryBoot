package com.example.dto;

import java.time.LocalDateTime;

public class StudentTakenBookInfo {
    private Integer id;
    private String studentName;
    private String studentSurname;
    private String studentPhone;
    private String bookTitle;
    private LocalDateTime takenDate;
    public StudentTakenBookInfo() {
    }
    public StudentTakenBookInfo(Integer id, String studentName, String studentSurname, String studentPhone, String bookTitle,
                                LocalDateTime takenDate) {
        this.id = id;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentPhone = studentPhone;
        this.bookTitle = bookTitle;
        this.takenDate = takenDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public LocalDateTime getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(LocalDateTime takenDate) {
        this.takenDate = takenDate;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentSurname='" + studentSurname + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", takenDate=" + takenDate +
                '}';
    }
}
