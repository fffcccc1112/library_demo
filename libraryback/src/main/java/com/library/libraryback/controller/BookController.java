package com.library.libraryback.controller;

import com.library.libraryback.entity.Book;
import com.library.libraryback.mapper.BookMapper;
import com.library.libraryback.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookService bookService;
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookMapper.selectAllBook();
    }
    @PostMapping("/insert")
    public ResponseEntity<String> insertBook(@RequestBody Book book) {
        try{
            bookService.insertBook(book);
            if( bookService.insertBook(book)==1){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Book added successfully: ");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed insert book");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed insert book");
        }
    }
    @GetMapping("/bookdel/{id}")
    public ResponseEntity<Integer> deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }
}
