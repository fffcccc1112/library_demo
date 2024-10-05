package com.library.libraryback.mapper;

import com.library.libraryback.entity.User;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where userid=#{id}")
    public User getUserById(int id);
}