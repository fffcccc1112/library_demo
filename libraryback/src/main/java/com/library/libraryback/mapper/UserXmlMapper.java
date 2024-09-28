package com.library.libraryback.mapper;

import com.library.libraryback.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserXmlMapper {
    List<User> getAllUser();
    int addUser(User user);
     User getUserById(int id);
     User getUserByUsername(String username);
    int deleteUserById(int id);
    int upDate(User user);
}
