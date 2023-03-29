package com.example.dto;

import org.example.enums.BookStatus;

import java.time.LocalDateTime;

public class StudentBookHistory extends StudentsBookInfo{
    private BookStatus status;
    private LocalDateTime retunedDate;
    public StudentBookHistory() {

    }
    public StudentBookHistory(BookStatus status, LocalDateTime retunedDate) {
        super();
        this.status = status;
        this.retunedDate = retunedDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public LocalDateTime getRetunedDate() {
        return retunedDate;
    }

    public void setRetunedDate(LocalDateTime retunedDate) {
        this.retunedDate = retunedDate;
    }

    @Override
    public String toString() {
        return "StudentBookHistory{" +
                "status=" + status +
                ", retunedDate=" + retunedDate + super.toString()+
                '}';
    }
}
