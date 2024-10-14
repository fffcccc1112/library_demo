package com.library.libraryback.controller;

import com.library.libraryback.entity.Book;
import com.library.libraryback.entity.BookBorrow;
import com.library.libraryback.entity.Result;
import com.library.libraryback.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin
@RequestMapping("/book")
@RestController

public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public List<Book> getBooks() {
        /**
         * 查询所有图书
         */
        return bookService.getBooks();
    }
    /**
     * 根据id查询图书
     */
    @GetMapping("/find/{id}")
    public Result getBookById(@PathVariable("id") int id) {
        return Result.success(bookService.getBookById(id));
    }
    /**
     * 新增图书
     */
    @PostMapping("/add")
    public Result insertBook(@RequestBody Book book) {
        if(bookService.getBookById(book.getBookid())==null){
            return Result.success(bookService.insertBook(book));
        }
        else{
            return Result.error("不能重复编号!");
        }
    }
    /**
     * 根据图书编号删除图书
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }
    /**
     * 更新图书
    */
    @PutMapping("/update")
    public Integer updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
    /**
     * 用户借阅记录
     */
    @PostMapping("/borrow")
    public Result borrowBook(@RequestBody BookBorrow bookBorrow) {
        Book book = bookService.getBookById(bookBorrow.getBookid());
        if(book.isExist()){
            //设置借阅人
            book.setBorrow(bookBorrow.getUsername());
            LocalDateTime time = LocalDateTime.now();
            //设置借阅时间
            book.setOuttime(time);
            book.setExist(false);
            return Result.success(bookService.updateBook(book));
        }
        else {
            return Result.error("该书已被借阅");
        }

  }
        /**
         * 还书
         */
        @GetMapping("/return/{bookid}")
        public Result returnBook(@PathVariable("bookid") int bookid, @RequestAttribute("userid") int userid) {
            Book book = bookService.getBookById(bookid);
            if(book.isExist()){
               return Result.error("该书未被借阅");
            }
            else {
                book.setExist(true);
                book.setBorrow(null);
                book.setOuttime(null);
                bookService.updateBook(book);
                return Result.success("还书成功");
            }
        }
        /**
         * 根据借阅情况查询图书
         */
        @GetMapping("/findstatus/falg")
        public Result findStatusFalg(@PathVariable("falg") boolean falg) {
            return null;
        }
}
