package com.example.service;

import org.example.dto.*;
import org.example.enums.BookStatus;
import org.example.repository.BookRepository;
import org.example.repository.StudentsBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StudentsBookService {
    @Autowired
    private StudentsBookRepository studentsBookRepository;
    @Autowired
    private BookRepository bookRepository;
    public void takeBook(Integer id, Integer duration, Student student) {
        int count = studentsBookRepository.getStudentsBookById(id).size();
        Book book = bookRepository.getBookById(id);
        if (book == null){
            System.out.println("\n Book not found \n Please, try again to another book");
            return;
        } else if (count > 5) {
            System.out.println("You had 5 books!");
            return;
        }
        StudentsBook studentBook = new StudentsBook();
        studentBook.setCreatedDate(LocalDateTime.now());
        studentBook.setStatus(BookStatus.TAKEN);
        LocalDateTime localDateTime = LocalDateTime.now();
        studentBook.setReturnedDate(localDateTime);
        studentBook.setDuration(duration);
        studentBook.setBookId(book);
        studentBook.setStudentId(student);

        studentsBookRepository.save(studentBook);
        book.setAmount(book.getAmount() - 1);
        bookRepository.updateBook(book.getId(),book);

        System.out.println("You taken book.");
    }

    public void userTakenBook() {
        List<StudentsBookInfo> studentsBookInfoListList = studentsBookRepository.userTakenList();
        if (studentsBookInfoListList == null) {
            System.out.println("No books yet.");
        }
        for (StudentsBookInfo studentsBookInfo : studentsBookInfoListList) {
            System.out.println(studentsBookInfo);
        }
        }

    public void userReturnedBookList() {
        List<StudentsBook> studentsBookList = studentsBookRepository.userReturnedBookList();
        if (studentsBookList == null) {
            System.out.println("No books yet");
        }
        for (StudentsBook studentsBook : studentsBookList) {
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
        Book book = bookRepository.getBookById(bookId);
        if (book == null) {
            System.out.println("Not fount book.");
        }
        studentsBookRepository.returnBook(bookId);
    }
}
