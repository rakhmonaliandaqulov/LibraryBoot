package com.example.controller;

import com.example.entity.BookEntity;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping(value = "book")
public class BookController {
    @Autowired
    private BookService bookService;
    private List<BookEntity> bookEntityList = new LinkedList<>();
    public BookController() {
        BookEntity s1 = new BookEntity();
        s1.setId(UUID.randomUUID().toString());
        s1.setTitle("O'tgan kunlar");
        s1.setAuthor("Ali Aliyev");
        s1.setAmount(12);
        s1.setPublishYear("2003-12-12");
        s1.setVisible(Boolean.TRUE);

        bookEntityList.add(s1);
    }
    @GetMapping(value = "/list")
    public List<BookEntity> getAll() {
        return bookService.bookList();
    }

    @GetMapping(value = "/get/{id}")
    public BookEntity getById(@PathVariable("id") String id) {
        Optional<BookEntity> optional = bookEntityList.stream()
                .filter(studentDTO -> studentDTO.getId().equals(id))
                .findAny();
        return optional.orElse(null);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Boolean delete(@PathVariable("id") String id) {
        return bookService.deleteBook(id);
    }


    @PostMapping(value = "/create")
    public BookEntity create(@RequestBody BookEntity book) {
        book.setId(UUID.randomUUID().toString());
        bookService.addBook(book);
        return book;
    }

    @PostMapping(value = "/create/all")
    public Boolean createAll(@RequestBody List<BookEntity> list) {
        for (BookEntity book : list) {
            book.setId(UUID.randomUUID().toString());
            bookService.addBook(book);
        }
        return true;
    }

    /*@PutMapping(value = "/update/{id}")
    public Boolean update(@PathVariable("id")String id, @RequestBody BookEntity studentDTO) {
        for (BookEntity dto : studentDTOList) {
            if (dto.getId().equals(id)) {
                dto.setName(studentDTO.getName());
                dto.setSurname(studentDTO.getSurname());
                return true;
            }
        }
        return false;
    }*/
}
