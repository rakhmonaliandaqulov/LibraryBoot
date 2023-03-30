package com.example.service;

import com.example.dto.BookDto;
import com.example.dto.StudentBookDto;
import com.example.dto.StudentDTO;
import com.example.entity.BookEntity;
import com.example.entity.StudentBookEntity;
import com.example.entity.StudentEntity;
import com.example.enums.BookStatus;
import com.example.repository.BookRepository;
import com.example.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public class StudentBookService {
    @Autowired
    private StudentBookRepository studentsBookRepository;
    @Autowired
    private BookRepository bookRepository;
    public void takeBook(Integer id, Integer duration, StudentEntity student) {
        int count = studentsBookRepository.getStudentsBookById(id).size();
        BookEntity book = bookRepository.getBookById(id);
        if (book == null){
            System.out.println("\n Book not found \n Please, try again to another book");
            return;
        } else if (count > 5) {
            System.out.println("You had 5 books!");
            return;
        }
        StudentBookEntity studentBook = new StudentBookEntity();
        studentBook.setCreatedDate(LocalDateTime.now());
        studentBook.setStatus(BookStatus.TAKEN);
        LocalDateTime localDateTime = LocalDateTime.now();
        studentBook.setReturnedDate(localDateTime);
        studentBook.setDuration(duration);
        studentBook.setBookId(book.getId());
        studentBook.setStudentId(student.getId());

        studentsBookRepository.save(studentBook);
        book.setAmount(book.getAmount() - 1);
        bookRepository.updateBook(book.getId());

        System.out.println("You taken book.");
    }

  /*  public void userTakenBook() {
        List<StudentsBookInfo> studentsBookInfoListList = studentsBookRepository.userTakenList();
        if (studentsBookInfoListList == null) {
            System.out.println("No books yet.");
        }
        for (StudentsBookInfo studentsBookInfo : studentsBookInfoListList) {
            System.out.println(studentsBookInfo);
        }
    }

    public void userReturnedBookList() {
        List<StudentBookDto> studentsBookList = studentsBookRepository.userReturnedBookList();
        if (studentsBookList == null) {
            System.out.println("No books yet");
        }
        for (StudentBookDto studentsBook : studentsBookList) {
            System.out.println(studentsBook);
        }
    }

    public void userHistoryLibrary() {
        List<StudentBookHistory> studentBookHistoryList = studentsBookRepository.userHistoryLibrary();
        if (studentBookHistoryList == null) {
            System.out.println("No history yet");
        }
        for (StudentBookHistory studentBookHistory : studentBookHistoryList) {
            System.out.println(studentBookHistory);
        }
    }

    public void adminTakenBookStudentList() {
        List<StudentTakenBookInfo> studentInfoList = studentsBookRepository.adminTakenBookStudentList();
        if (studentInfoList == null) {
            System.out.println("No taken book student list yet.");
        }
        for (StudentTakenBookInfo studentInfo : studentInfoList) {
            System.out.println(studentInfo);
        }
    }
    public void adminBookAllHistory() {
        List<StudentAllHistory> studentAllHistoryListt = studentsBookRepository.adminStudentAllHistory();
        if (studentAllHistoryListt == null) {
            System.out.println("No book all history yet");
        }
        for (StudentAllHistory studentAllHistory : studentAllHistoryListt) {
            System.out.println(studentAllHistory);
        }
    }

    public void returnBook(Integer bookId) {
        BookEntity book = bookRepository.getBookById(bookId);
        if (book == null) {
            System.out.println("Not fount book.");
        }
        studentsBookRepository.returnBook(bookId);
    }*/
}
