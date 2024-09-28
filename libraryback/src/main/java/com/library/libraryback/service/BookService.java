package com.library.libraryback.service;

import com.library.libraryback.entity.Book;
import org.springframework.http.ResponseEntity;

public interface BookService {
    int insertBook(Book book);
    ResponseEntity<Integer> deleteBook(int id);
}
