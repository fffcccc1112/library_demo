package com.library.libraryback.mapper;

import com.library.libraryback.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserXmlMapper {
    List<User> getAllUser();
    User addUser(String username,String password,String phone);
     User getUserById(int id);
     User getUserByUsername(String username);
    User deleteUserById(int id);
    int upDate(User user);
    User getUserByPhone(String phone);

}
