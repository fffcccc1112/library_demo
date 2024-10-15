package com.library.libraryback.service;

import com.library.libraryback.entity.Book;
import com.library.libraryback.mapper.BookMapper;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    public Integer insertBook(Book book){
     return bookMapper.insertBook(book);
    }

    @Override
    public ResponseEntity<Integer> deleteBook(int id) {
        try {
            int affectedRows = bookMapper.deleteBook(id);
            if (affectedRows > 0) {
                // 如果影响的行数大于0，表示删除成功
                return new ResponseEntity<>(affectedRows, HttpStatus.OK);
            } else {
                // 如果影响的行数为0，表示没有找到要删除的记录
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
            // 可以返回一个错误状态码，例如INTERNAL_SERVER_ERROR
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Book> getBooks() {
        return bookMapper.selectAllBook();
    }

    @Override
    public Book getBookById(int id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public Integer updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public List<Book> findtrue() {
      return bookMapper.findtrue();
    }

    @Override
    public List<Book> findfalse() {
        return bookMapper.findfalse();
    }

    @Override
    public String findUrl(int id) {
       return bookMapper.findUrl(id).getCover();
    }
}
