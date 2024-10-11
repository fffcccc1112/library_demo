package com.library.libraryback.mapper;

import com.library.libraryback.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    List<Book> selectAllBook();
    Integer insertBook(Book book);
    int deleteBook(int id);
    Book getBookById(int id);
    Integer updateBook(Book book);
    List<Book>findtrue();
    List<Book> findfalse();
}
