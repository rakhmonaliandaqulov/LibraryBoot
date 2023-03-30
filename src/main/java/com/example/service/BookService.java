package com.example.service;

import com.example.entity.BookEntity;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public void addBook(String title, String author, String publish_year, Integer amount) {
        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishYear(publish_year);
        book.setAmount(amount);

        int n = bookRepository.save(book);
        if (n != 0) {
            System.out.println("\n Book added succesfully!");
            return;
        }else System.out.println("Error.");
    }

    public void bookList() {
        List<BookEntity> bookList = bookRepository.bookList();
        if (bookList == null) {
            System.out.println("\n No book yet.");
        }
        for (BookEntity book : bookList) {
            System.out.println(book);
        }
    }

    public void deleteBook(int bookId) {
        BookEntity book = bookRepository.getBookById(bookId);
        if (book == null) {
            System.out.println("\n Not found book.");
            return;
        }
        int n = bookRepository.deleteBook(bookId);
        if (n != 0) {
            System.out.println("\n Deleted book");
        }

    }
}
