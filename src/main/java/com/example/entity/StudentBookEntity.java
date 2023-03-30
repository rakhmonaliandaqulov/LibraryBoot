package com.example.entity;

import com.example.enums.BookStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "student_book")
public class StudentBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createdDate;
    private BookStatus status;
    private LocalDateTime returnedDate;
    private Integer duration;
    private Integer bookId;
    private Integer studentId;
}
