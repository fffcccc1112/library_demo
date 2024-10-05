package com.library.libraryback.controller;

import com.library.libraryback.entity.Book;
import com.library.libraryback.entity.Result;
import com.library.libraryback.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;
//查询所有图书
    @GetMapping("/all")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
//根据id查询图书
    @GetMapping("/find/{id}")
    public Result getBookById(@PathVariable("id") int id) {
        return Result.success(bookService.getBookById(id));
    }
//新增图书
    @PostMapping("/add")
    public Book insertBook(@RequestBody Book book) {
           return bookService.insertBook(book);
    }
//根据图书编号删除图书
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }
//更新图书
    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

 //用户借阅记录
  @PostMapping("/borrow")
 public Result borrowBook(String username, int bookid) {
        Book book = bookService.getBookById(bookid);
        if(book.isExist()==true){
            //设置借阅人
            book.setBorrow(username);
            LocalDateTime time = LocalDateTime.now();
            //设置借阅时间
            book.setOuttime(time);
            book.setExist(false);
            bookService.updateBook(book);
            return Result.success(book);
        }
        else {
            return Result.error("该书已被借阅");
        }

  }
  //用户归还图书
    @GetMapping("/return")
    public Book returnBook(int bookid) {
        Book book = bookService.getBookById(bookid);
        book.setExist(true);
        book.setBorrow(null);
        book.setOuttime(null);
        return  bookService.updateBook(book);
    }
}
