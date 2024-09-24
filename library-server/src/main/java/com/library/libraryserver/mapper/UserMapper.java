package com.library.libraryserver.mapper;

import com.library.libraryserver.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    public int addUser(User user);
    public int updateUser(User user);
    public int deleteUser(int id);
    public List<User> findAll();
}
