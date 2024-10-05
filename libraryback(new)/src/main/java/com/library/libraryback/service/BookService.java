package com.library.libraryback.service;

import com.library.libraryback.entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    Book insertBook(Book book);
    ResponseEntity<Integer> deleteBook(int id);
    List<Book> getBooks();
    Book getBookById(int id);
    Book updateBook(Book book);
}
