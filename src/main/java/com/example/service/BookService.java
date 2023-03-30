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
    public void addBook(BookEntity book) {
        book.setTitle(book.getTitle());
        book.setAuthor(book.getAuthor());
        book.setPublishYear(book.getPublishYear());
        book.setAmount(book.getAmount());

        int n = bookRepository.save(book);
        if (n != 0) {
            System.out.println("\n Book added succesfully!");
            return;
        }else System.out.println("Error.");
    }

    public List<BookEntity> bookList() {
        List<BookEntity> bookList = bookRepository.bookList();
        if (bookList == null) {
            System.out.println("\n No book yet.");
        }
        for (BookEntity book : bookList) {
            System.out.println(book);
        }
        return bookList;
    }

    public Boolean deleteBook(String bookId) {
        BookEntity book = bookRepository.getBookById(bookId);
        if (book == null) {
            System.out.println("\n Not found book.");
            return null;
        }
        int n = bookRepository.deleteBook(bookId);
        if (n != 0) {
            System.out.println("\n Deleted book");
        }

        return null;
    }
}
