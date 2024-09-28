package com.library.libraryback.mapper;

import com.library.libraryback.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    List<Book> selectAllBook();
    int insertBook(Book book);
    int deleteBook(int id);
}
